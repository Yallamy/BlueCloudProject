package br.com.sigad.msc.interessado.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.sigad.msc.interessado.entity.HistoricoInteressado;
import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.enums.StatusEnum;
import br.com.sigad.msc.interessado.enums.TipoDocumentoEnum;
import br.com.sigad.msc.interessado.enums.TipoHistoricoEnum;
import br.com.sigad.msc.interessado.exception.ServiceValidacao;
import br.com.sigad.msc.interessado.exception.SigadException;
import br.com.sigad.msc.interessado.repository.InteressadoRepository;
import br.com.sigad.msc.interessado.service.HistoricoInteressadoService;
import br.com.sigad.msc.interessado.service.InteressadoService;
import br.com.twsoftware.alfred.cnpj.CNPJ;
import br.com.twsoftware.alfred.cpf.CPF;
import br.com.twsoftware.alfred.email.Email;
import br.com.twsoftware.alfred.object.Objeto;
import br.com.twsoftware.alfred.telefones.Telefones;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que implementa os métodos do serviço para manter um interessado.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Service
@Transactional
@Slf4j
public class InteressadoServiceImpl implements InteressadoService {
	
	@Autowired
	private InteressadoRepository repository;
	
	@Autowired
	private HistoricoInteressadoService historicoService;
	
	private Validator validator;
	
	private Gson gson;
	
	@PostConstruct
	public void init() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	/*
	 *
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#create(br.com.sigad.msc.interessado.entity.Interessado)
	 */
	@Override
	public Interessado create(Interessado interessado) throws SigadException {

		if(Objeto.isBlank(interessado)) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}
		
		interessado.setSituacao(StatusEnum.ATIVO);
		Set<ConstraintViolation<Interessado>> violations = validator.validate(interessado);
		
		if(violations.size() > BigDecimal.ZERO.intValue()) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}
		
		//validações de regras de negócio
		validarRegrasNegocio(interessado);
		
		Interessado registro = repository.findByCpfCnpj(interessado.getCpfCnpj()); 
		
		if(Objeto.notBlank(registro)) {
			throw new SigadException(ServiceValidacao.CPF_CNPJ_JA_CADASTRADO);
		}
		
		//salvando
		Interessado interessadoSalvo = repository.save(interessado);
		
		HistoricoInteressado historico = HistoricoInteressado.builder()
				.dtAlteracao(Calendar.getInstance().getTime())
				.tipoHistorico(TipoHistoricoEnum.CREATE)
				.dadosJson(gson.toJson(interessadoSalvo))
				.interessado(interessadoSalvo)
				.build();
		
		historicoService.create(historico);
		
		return interessadoSalvo;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#update(br.com.sigad.msc.interessado.entity.Interessado)
	 */
	@Override
	public void update(Interessado interessado) throws SigadException {
		
		Interessado registroAnterior = null;
		Interessado interessadoPorCpfCnpj = null;

		if(Objeto.isBlank(interessado)) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}

		Set<ConstraintViolation<Interessado>> violations = validator.validate(interessado);

		if(violations.size() > BigDecimal.ZERO.intValue()
				|| Objeto.isBlank(interessado.getId())) {

			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}

		//validações de regras de negócio
		validarRegrasNegocio(interessado);

		try {

			registroAnterior = repository.findById(interessado.getId()).get();
			interessadoPorCpfCnpj = repository.findByCpfCnpj(interessado.getCpfCnpj());

		} catch(NoSuchElementException ex) {
			throw new SigadException(ServiceValidacao.INTERESSADO_NAO_ENCONTRADO);
		}
		
		//verificar se o interessado está inativo
		if(StatusEnum.INATIVO.equals(registroAnterior.getSituacao())) {
			throw new SigadException(ServiceValidacao.INTERESSADO_INATIVO);
		}
		
		//verificar se o CPF/CNPJ já está sendo usado por outro interessado
		if(Objeto.notBlank(interessadoPorCpfCnpj) 
				&& !registroAnterior.getId().equals(interessadoPorCpfCnpj.getId())) {

			throw new SigadException(ServiceValidacao.CPF_CNPJ_JA_CADASTRADO);
		}

		//salvando
		HistoricoInteressado historico = HistoricoInteressado.builder()
				.dtAlteracao(Calendar.getInstance().getTime())
				.tipoHistorico(TipoHistoricoEnum.UPDATE)
				.dadosJson(gson.toJson(interessado))
				.interessado(interessado)
				.build();
		
		historicoService.create(historico);

		repository.save(interessado);
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#retrieve(java.lang.Long)
	 */
	@Override
	public Interessado retrieve(Long id) throws SigadException {
		
		if(Objeto.isBlank(id)) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}

		try {

			return repository.findById(id).get();

		} catch(NoSuchElementException ex) {
			throw new SigadException(ServiceValidacao.INTERESSADO_NAO_ENCONTRADO);
		}
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#retrieve(java.lang.String)
	 */
	@Override
	public Interessado retrieve(String cpfCnpj) throws SigadException {
		
		if(Objeto.isBlank(cpfCnpj)) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}
		
		Interessado response = repository.findByCpfCnpj(cpfCnpj);
		
		if(Objeto.isBlank(response)) {
			throw new SigadException(ServiceValidacao.INTERESSADO_NAO_ENCONTRADO);
		}
		
		return response;
		
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#delete(br.com.sigad.msc.interessado.entity.Interessado)
	 */
	@Override
	public void delete(Interessado interessado) throws SigadException {
		
		Interessado interessadoSalvo = null;
		
		if(Objeto.isBlank(interessado) || Objeto.isBlank(interessado.getId())) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}
		
		try {
			
			interessadoSalvo = repository.findById(interessado.getId()).get(); 

		} catch(NoSuchElementException ex) {
			throw new SigadException(ServiceValidacao.INTERESSADO_NAO_ENCONTRADO);
		}
		
		if(StatusEnum.INATIVO.equals(interessadoSalvo.getSituacao())) {
			throw new SigadException(ServiceValidacao.INTERESSADO_INATIVO);
		}
		
		//salvando
		interessadoSalvo.setSituacao(StatusEnum.INATIVO);
		String dadosJson = gson.toJson(interessadoSalvo);
		
		log.info("Deletando o interessado: " + dadosJson);
		
		HistoricoInteressado historico = HistoricoInteressado.builder()
				.dtAlteracao(Calendar.getInstance().getTime())
				.tipoHistorico(TipoHistoricoEnum.DELETE)
				.dadosJson(gson.toJson(dadosJson))
				.interessado(interessado)
				.build();
		
		historicoService.create(historico);
		repository.save(interessadoSalvo);
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#list(br.com.sigad.msc.interessado.entity.Interessado, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Interessado> list(Interessado interessado, Pageable pageable) throws SigadException {
		
		if(Objeto.isBlank(pageable)) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}

		if(Objeto.isBlank(interessado)) {
			interessado = Interessado.builder().build();
		}

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<Interessado> example = Example.of(interessado, matcher);

		return repository.findAll(example, pageable); 
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#listHistoric(br.com.sigad.msc.interessado.entity.Interessado, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<HistoricoInteressado> listHistoric(Interessado interessado, Pageable pageable) throws SigadException {
		
		return historicoService.list(interessado, pageable);
	}
	
	/**
	 * Método que valida as regras de negócio.
	 * @param interessado - objeto a ser validado
	 * @throws SigadException - lança uma exception caso a regra de negócio 
	 * não esteja sendo atendida.
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 3 de out. de 2020
	 */
	private void validarRegrasNegocio(Interessado interessado) throws SigadException {
		
		if(TipoDocumentoEnum.CPF.equals(interessado.getTipoDocumento()) 
				&& !CPF.isValido(interessado.getCpfCnpj())) {
			
			throw new SigadException(ServiceValidacao.CPF_INVALIDO);
		}
		
		if(TipoDocumentoEnum.CNPJ.equals(interessado.getTipoDocumento()) 
				&& !CNPJ.isValido(interessado.getCpfCnpj())) {
			
			throw new SigadException(ServiceValidacao.CNPJ_INVALIDO);
		}
		
		if(Objeto.notBlank(interessado.getEmail()) 
				&& !Email.isValido(interessado.getEmail())) {
			
			throw new SigadException(ServiceValidacao.EMAIL_INVALIDO);
		}
		
		if(Objeto.notBlank(interessado.getTelefone()) 
				&& !Telefones.isValido(interessado.getTelefone())) {
			
			throw new SigadException(ServiceValidacao.TELEFONE_INVALIDO);
		}
	}

}
