package br.com.sigad.msc.interessado.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.sigad.msc.interessado.EntityGenericUtil;

/**
 * Classe de teste que representa os cenários de testes da classe {@link HistoricoResponseDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class HistoricoResponseDTOTest {

	private Long id;

	private Date dtAlteracao;

	private String tipoHistorico;

	private String dadosJson;
	
	private HistoricoResponseDTO response;

	@BeforeEach
	public void setup() {

		this.id = EntityGenericUtil.getLong();
		this.dtAlteracao = EntityGenericUtil.getDate();
		this.tipoHistorico = EntityGenericUtil.getString();
		this.dadosJson = EntityGenericUtil.getString();
		
		response = new HistoricoResponseDTO(this.id, this.dtAlteracao, 
				this.tipoHistorico, this.dadosJson);
	}

	@Test
	public void getInstanceTest() {

		assertNotNull(response);
		assertEquals(this.id, response.getId());
		assertEquals(this.dtAlteracao, response.getDtAlteracao());
		assertEquals(this.tipoHistorico, response.getTipoHistorico());
		assertEquals(this.dadosJson, response.getDadosJson());
	}	

	@Test
	public void getInstanceVaziaTest() {

		response = new HistoricoResponseDTO();

		assertNotNull(response);
		assertEquals(null, response.getId());
		assertEquals(null, response.getDtAlteracao());
		assertEquals(null, response.getTipoHistorico());
		assertEquals(null, response.getDadosJson());
	}

	@Test
	public void setAndGetAllFieldsTest() {

		response = new HistoricoResponseDTO();
		
		response.setId(this.id);
		response.setDtAlteracao(this.dtAlteracao);
		response.setTipoHistorico(this.tipoHistorico);
		response.setDadosJson(this.dadosJson);

		assertEquals(this.id, response.getId());
		assertEquals(this.dtAlteracao, response.getDtAlteracao());
		assertEquals(this.tipoHistorico, response.getTipoHistorico());
		assertEquals(this.dadosJson, response.getDadosJson());
	}

	@Test
	public void getEqualsTest() {

		HistoricoResponseDTO response2 = new HistoricoResponseDTO(this.id, this.dtAlteracao, 
				this.tipoHistorico, this.dadosJson);

		assertNotNull(response);
		assertNotNull(response2);
		assertEquals(response, response2);
	}

	@Test
	public void getHashCodeTest() {

		HistoricoResponseDTO response2 = new HistoricoResponseDTO(this.id, this.dtAlteracao, 
				this.tipoHistorico, this.dadosJson);

		assertNotNull(response);
		assertNotNull(response2);
		assertEquals(response.hashCode(), response2.hashCode());
	}

	@Test
	public void getToStringTest() {

		assertNotNull(response);
		assertNotNull(response.toString());
	}
}
