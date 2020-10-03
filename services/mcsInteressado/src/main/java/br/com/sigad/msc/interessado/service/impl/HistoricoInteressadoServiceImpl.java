package br.com.sigad.msc.interessado.service.impl;

import java.math.BigDecimal;
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

import br.com.sigad.msc.interessado.entity.HistoricoInteressado;
import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.exception.ServiceValidacao;
import br.com.sigad.msc.interessado.exception.SigadException;
import br.com.sigad.msc.interessado.repository.HistoricoInteressadoRepository;
import br.com.sigad.msc.interessado.service.HistoricoInteressadoService;
import br.com.twsoftware.alfred.object.Objeto;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que implementa os métodos do serviço para manter o histórico do interessado.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 2 de out. de 2020
 */
@Service
@Transactional
@Slf4j
public class HistoricoInteressadoServiceImpl implements HistoricoInteressadoService {
	
	@Autowired
	private HistoricoInteressadoRepository repository;
	
	private Validator validator;
	
	@PostConstruct
	public void init() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.HistoricoInteressadoService#create(br.com.sigad.msc.interessado.entity.HistoricoInteressado)
	 */
	@Override
	public HistoricoInteressado create(HistoricoInteressado historico) throws SigadException {
		
		if(Objeto.isBlank(historico)) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}

		Set<ConstraintViolation<HistoricoInteressado>> violations = validator.validate(historico);

		if(violations.size() > BigDecimal.ZERO.intValue()) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}

		return repository.save(historico);
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.HistoricoInteressadoService#list(br.com.sigad.msc.interessado.entity.Interessado, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<HistoricoInteressado> list(Interessado interessado, Pageable pageable) throws SigadException {
		
		if(Objeto.isBlank(interessado) || Objeto.isBlank(pageable)) {
			throw new SigadException(ServiceValidacao.BAD_REQUEST);
		}
		
		HistoricoInteressado historico = HistoricoInteressado.builder()
				.interessado(interessado).build();

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<HistoricoInteressado> example = Example.of(historico, matcher);
		
		return repository.findAll(example, pageable);
	}

}
