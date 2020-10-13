package br.com.sigad.msc.interessado.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sigad.msc.interessado.EntityGenericUtil;
import br.com.sigad.msc.interessado.entity.HistoricoInteressado;
import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.enums.StatusEnum;
import br.com.sigad.msc.interessado.enums.TipoDocumentoEnum;
import br.com.sigad.msc.interessado.exception.SigadException;
import br.com.sigad.msc.interessado.repository.InteressadoRepository;
import br.com.sigad.msc.interessado.service.HistoricoInteressadoService;


/**
 * Classe de teste que representa os cenários de testes da classe {@link InteressadoServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@SpringBootTest
public class InteressadoServiceImplTest {
	
	@InjectMocks
	private InteressadoServiceImpl interessadoServiceImpl;
	
	@Mock
	private InteressadoRepository repository;
	
	@Mock
	private HistoricoInteressadoService historicoInteressadoService;
	
	@Mock
	private Pageable pageable;
	
	@Mock
	private Page<Interessado> page;
	
	@Mock
	private Page<HistoricoInteressado> pageHistorico;
	
	@Mock
	private HistoricoInteressado historico;
	
	private Optional<Interessado> interessadoOptionalResponse;
	
	private Interessado request;
	
	private Interessado interessadoResponse;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() {
		
		this.request = Interessado.builder()
				.nome(EntityGenericUtil.getString())
				.cpfCnpj(EntityGenericUtil.getCPF())
				.tipoDocumento(TipoDocumentoEnum.CPF)
				.email(EntityGenericUtil.getEmail())
				.telefone(EntityGenericUtil.getTelefone())
				.build();
		
		this.interessadoResponse = Interessado.builder()
				.id(EntityGenericUtil.getLong())
				.nome(EntityGenericUtil.getString())
				.cpfCnpj(EntityGenericUtil.getCPF())
				.tipoDocumento(TipoDocumentoEnum.CPF)
				.email(EntityGenericUtil.getEmail())
				.telefone(EntityGenericUtil.getTelefone())
				.situacao(StatusEnum.ATIVO)
				.build();
		
		this.interessadoOptionalResponse = Optional.of(this.interessadoResponse);

		Mockito.when(this.repository.save(
				Mockito.any(Interessado.class))).thenReturn(this.interessadoResponse);
		Mockito.when(this.repository.findByCpfCnpj(
				Mockito.any(String.class))).thenReturn(null);
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(this.interessadoOptionalResponse);
		Mockito.when(this.repository.findAll(
				Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(this.page);
		Mockito.when(this.historicoInteressadoService.create(
				Mockito.any(HistoricoInteressado.class))).thenReturn(this.historico);
		Mockito.when(this.historicoInteressadoService.list(
				Mockito.any(Interessado.class), Mockito.any(Pageable.class))).thenReturn(this.pageHistorico);
	}
	
	//create
	@Test
	public void createTest() {
		
		this.interessadoServiceImpl.init();
		Interessado response = this.interessadoServiceImpl.create(request);

		assertNotNull(response);
		assertNotNull(response.getId());
	}
	
	@Test()
	public void createInteressadoNullTest() {

		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(null);
		});
	}
	
	@Test()
	public void createInteressadoComCNPJTest() {
		
		this.request.setCpfCnpj(EntityGenericUtil.getCNPJ());
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoJaCadastradaTest() {
		
		Mockito.when(this.repository.findByCpfCnpj(
				Mockito.any(String.class))).thenReturn(this.interessadoResponse);
		
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoSemNomeTest() {

		this.request.setNome(null);
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoCPFCNPJNullTest() {
		
		this.request.setCpfCnpj(null);
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoCPFInvalidoTest() {
		
		this.request.setCpfCnpj(EntityGenericUtil.getCPF().concat("1"));
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoCNPJInvalidoTest() {
		
		this.request.setCpfCnpj(EntityGenericUtil.getCNPJ().concat("1"));
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoTipoDocumentoNullTest() {
		
		this.request.setTipoDocumento(null);
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoComEmailInvalidoTest() {

		this.request.setEmail(EntityGenericUtil.getString());
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createInteressadoComTelefoneInvalidoTest() {

		this.request.setTelefone(EntityGenericUtil.getString());
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.create(request);
		});
	}
	
	//update
	@Test
	public void updateTest() {

		this.request.setId(EntityGenericUtil.getLong());
		
		this.interessadoServiceImpl.init();
		this.interessadoServiceImpl.update(request);
	}
	
	@Test()
	public void updateInteressadoNullTest() {
		
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(null);
		});
	}
	
	@Test()
	public void updateInteressadoJaCadastradaTest() {
		
		this.interessadoResponse.setId(EntityGenericUtil.getLong());
		this.request.setId(EntityGenericUtil.getLong());
		this.interessadoServiceImpl.init();
		
		Mockito.when(this.repository.findByCpfCnpj(
				Mockito.any(String.class))).thenReturn(this.interessadoResponse);

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updateInteressadoNotFoundTest() {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);

		this.request.setId(EntityGenericUtil.getLong());
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updateInteressadoInativoTest() {
		
		this.interessadoResponse.setSituacao(StatusEnum.INATIVO);
		this.interessadoOptionalResponse = Optional.of(this.interessadoResponse);
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(this.interessadoOptionalResponse);

		this.request.setId(EntityGenericUtil.getLong());
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updateInteressadoSemNomeTest() {

		this.request.setId(EntityGenericUtil.getLong());
		this.request.setNome(null);
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updateInteressadoSemCPFCNPJTest() {

		this.request.setId(EntityGenericUtil.getLong());
		this.request.setCpfCnpj(null);
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updateInteressadoCPFInvalidoTest() {

		this.request.setId(EntityGenericUtil.getLong());
		this.request.setCpfCnpj(EntityGenericUtil.getCPF().concat("1"));
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updateInteressadoCNPJInvalidoTest() {

		this.request.setId(EntityGenericUtil.getLong());
		this.request.setCpfCnpj(EntityGenericUtil.getCNPJ().concat("1"));
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updateInteressadoSemTipoDocumentoTest() {

		this.request.setId(EntityGenericUtil.getLong());
		this.request.setTipoDocumento(null);
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaComEmailInvalidoTest() {

		this.request.setId(EntityGenericUtil.getLong());
		this.request.setEmail(EntityGenericUtil.getString());
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	@Test()
	public void updatePessoaComTelefoneInvalidoTest() {

		this.request.setId(EntityGenericUtil.getLong());
		this.request.setTelefone(EntityGenericUtil.getString());
		this.interessadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.update(request);
		});
	}
	
	//retrieve
	@Test
	public void retrieveTest() {
		
		Interessado response = this.interessadoServiceImpl.retrieve(EntityGenericUtil.getLong());

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.interessadoResponse, response);
	}
	
	@Test()
	public void retrieveNotFoundTest() {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.retrieve(EntityGenericUtil.getLong());
		});
	}
	
	@Test()
	public void retrieveComNullTest() {
		
		Long id = null;
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.retrieve(id);
		});
	}
	
	//retrieve por CPF/CNPJ
	@Test
	public void retrieveByCpfCnpjTest() {
		
		Mockito.when(this.repository.findByCpfCnpj(
				Mockito.any(String.class))).thenReturn(this.interessadoResponse);

		Interessado response = this.interessadoServiceImpl.retrieve(EntityGenericUtil.getString());

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(this.interessadoResponse, response);
	}

	@Test()
	public void retrieveByCpfCnpjNotFoundTest() {

		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.retrieve(EntityGenericUtil.getString());
		});
	}

	@Test()
	public void retrieveByCpfCnpjComNullTest() {

		String cpfCnpj = null;

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.retrieve(cpfCnpj);
		});
	}
	
	//delete
	@Test
	public void deleteTest() {
	
		this.interessadoServiceImpl.init();
		this.interessadoServiceImpl.delete(this.interessadoResponse);
	}
	
	@Test()
	public void deleteNotFoundTest() {
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenThrow(NoSuchElementException.class);
		
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.delete(this.interessadoResponse);
		});
	}
	
	@Test()
	public void deleteInteressadoNullTest() {
		
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.delete(null);
		});
	}
	
	@Test()
	public void deleteInteressadoSemIdTest() {

		this.request.setId(null);
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.delete(request);
		});
	}
	
	@Test()
	public void deleteInteressadoInativoTest() {
		
		this.interessadoResponse.setSituacao(StatusEnum.INATIVO);
		this.interessadoOptionalResponse = Optional.of(this.interessadoResponse);
		
		Mockito.when(this.repository.findById(
				Mockito.any(Long.class))).thenReturn(this.interessadoOptionalResponse);

		this.request.setId(EntityGenericUtil.getLong());
		this.interessadoServiceImpl.init();
		
		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.delete(request);
		});
	}
	
	//list
	@Test
	public void listTest() {

		Interessado request = Interessado.builder()
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listComInteressadoNullTest() {
		
		Page<Interessado> response = this.interessadoServiceImpl.list(null, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test()
	public void listComPageableNullTest() {

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.list(this.request, null);
		});
	}
	
	@Test
	public void listPorIdTest() {

		Interessado request = Interessado.builder()
				.id(EntityGenericUtil.getLong())
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorNomeTest() {

		Interessado request = Interessado.builder()
				.nome(EntityGenericUtil.getString())
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorCPFTest() {

		Interessado request = Interessado.builder()
				.cpfCnpj(EntityGenericUtil.getCPF())
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorCNPJTest() {

		Interessado request = Interessado.builder()
				.cpfCnpj(EntityGenericUtil.getCNPJ())
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorTipoDocumentoTest() {

		Interessado request = Interessado.builder()
				.tipoDocumento(TipoDocumentoEnum.CPF)
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorEmailTest() {

		Interessado request = Interessado.builder()
				.email(EntityGenericUtil.getEmail())
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorTelefoneTest() {

		Interessado request = Interessado.builder()
				.telefone(EntityGenericUtil.getTelefone())
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listPorSituacaoTest() {

		Interessado request = Interessado.builder()
				.situacao(StatusEnum.ATIVO)
				.build();
		
		Page<Interessado> response = this.interessadoServiceImpl.list(request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	//list historic
	@Test
	public void listHistoricTest() {

		this.request.setId(EntityGenericUtil.getLong());

		Page<HistoricoInteressado> response = this.interessadoServiceImpl.listHistoric(
				request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@Test
	public void listHistoricNotFoundTest() {

		this.request.setId(EntityGenericUtil.getLong());

		Page<HistoricoInteressado> response = this.interessadoServiceImpl.listHistoric(
				request, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 0);
	}
	
	@Test
	public void listHistoricInteressadoNullTest() {
		
		Mockito.when(this.historicoInteressadoService.list(
				Mockito.any(Interessado.class), 
				Mockito.any(Pageable.class))).thenThrow(SigadException.class);

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.listHistoric(null, pageable);
		});
	}
	
	@Test
	public void listHistoricPageableNullTest() {

		assertThrows(SigadException.class, () -> {
			this.interessadoServiceImpl.listHistoric(request, null);
		});
	}

}
