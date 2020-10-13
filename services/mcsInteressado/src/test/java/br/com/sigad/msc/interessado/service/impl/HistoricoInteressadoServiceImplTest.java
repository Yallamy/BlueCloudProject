package br.com.sigad.msc.interessado.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.sigad.msc.interessado.EntityGenericUtil;
import br.com.sigad.msc.interessado.entity.HistoricoInteressado;
import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.enums.TipoHistoricoEnum;
import br.com.sigad.msc.interessado.exception.SigadException;
import br.com.sigad.msc.interessado.repository.HistoricoInteressadoRepository;

/**
 * Classe de teste que representa os cenários de testes da classe {@link HistoricoInteressadoServiceImpl}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@SpringBootTest
public class HistoricoInteressadoServiceImplTest {

	@InjectMocks
	private HistoricoInteressadoServiceImpl historicoInteressadoServiceImpl;

	@Mock
	private HistoricoInteressadoRepository repository;

	@Mock
	private Pageable pageable;

	@Mock
	private Page<HistoricoInteressado> page;

	@Mock
	private Interessado interessado;

	private List<HistoricoInteressado> listaHistorico;
	
	private HistoricoInteressado request;

	private HistoricoInteressado historicoResponse;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() {
		
		this.request = HistoricoInteressado.builder()
				.dtAlteracao(EntityGenericUtil.getDate())
				.tipoHistorico(TipoHistoricoEnum.CREATE)
				.dadosJson(EntityGenericUtil.getString())
				.interessado(interessado)
				.build();

		this.historicoResponse = HistoricoInteressado.builder()
				.id(EntityGenericUtil.getLong())
				.dtAlteracao(EntityGenericUtil.getDate())
				.tipoHistorico(TipoHistoricoEnum.CREATE)
				.dadosJson(EntityGenericUtil.getString())
				.interessado(interessado)
				.build();

		this.listaHistorico = new LinkedList<HistoricoInteressado>();
		this.listaHistorico.add(this.historicoResponse);

		Mockito.when(this.repository.save(
				Mockito.any(HistoricoInteressado.class))).thenReturn(this.historicoResponse);
		Mockito.when(this.repository.findAll(
				Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(this.page);
	}

	//create
	@Test
	public void createTest() {

		this.historicoInteressadoServiceImpl.init();
		HistoricoInteressado response = this.historicoInteressadoServiceImpl.create(request);

		assertNotNull(response);
		assertNotNull(response.getId());
	}

	@Test()
	public void createHistoricoNullTest() {

		this.historicoInteressadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.historicoInteressadoServiceImpl.create(null);
		});
	}

	@Test()
	public void createDtAlteracaoNullTest() {

		this.request.setDtAlteracao(null);

		this.historicoInteressadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.historicoInteressadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createTipoHistoricoNullTest()  {

		this.request.setTipoHistorico(null);

		this.historicoInteressadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.historicoInteressadoServiceImpl.create(request);
		});
	}
	
	@Test()
	public void createDadosJsonNullTest() {

		this.request.setDadosJson(null);

		this.historicoInteressadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.historicoInteressadoServiceImpl.create(request);
		});
	}

	@Test()
	public void createInteressadoNullTest() {

		this.request.setInteressado(null);

		this.historicoInteressadoServiceImpl.init();

		assertThrows(SigadException.class, () -> {
			this.historicoInteressadoServiceImpl.create(request);
		});
	}

	//list
	@Test
	public void listTest() {

		Page<HistoricoInteressado> response = 
				this.historicoInteressadoServiceImpl.list(this.interessado, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void listNotFoundTest() {

		List<HistoricoInteressado> listaHistorico = new LinkedList<HistoricoInteressado>();
		
		this.page = new PageImpl<HistoricoInteressado>(
				listaHistorico, Pageable.unpaged(), listaHistorico.size());
		
		Mockito.when(this.repository.findAll(
				Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(this.page);
		
		Page<HistoricoInteressado> response = this.historicoInteressadoServiceImpl.list(this.interessado, pageable);

		assertNotNull(response);
		assertNotNull(response.getContent().size() == 0);
	}

	@Test
	public void listComInteressadoNullTest() {

		assertThrows(SigadException.class, () -> {
			this.historicoInteressadoServiceImpl.list(null, pageable);
		});
	}

	@Test()
	public void listComPageableNullTest() {

		assertThrows(SigadException.class, () -> {
			this.historicoInteressadoServiceImpl.list(this.interessado, null);
		});
	}

}
