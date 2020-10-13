package br.com.sigad.msc.interessado.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.sigad.msc.interessado.EntityGenericUtil;

/**
 * Classe de teste que representa os cenários de testes da classe {@link InteressadoResponseDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class InteressadoResponseDTOTest {
	
	private Long id;

	private String nome;

	private String cpfCnpj;

	private String tipoDocumento;

	private String email;

	private String telefone;
	
	private String situacao;
	
	private InteressadoResponseDTO response;

	@BeforeEach
	public void setup() {

		this.id = EntityGenericUtil.getLong();
		this.nome = EntityGenericUtil.getString();
		this.cpfCnpj = EntityGenericUtil.getString();
		this.tipoDocumento = EntityGenericUtil.getString();
		this.email = EntityGenericUtil.getString();
		this.telefone = EntityGenericUtil.getString();
		this.situacao = EntityGenericUtil.getString();
		
		response = new InteressadoResponseDTO(this.id, this.nome, this.cpfCnpj, 
				this.tipoDocumento, this.email, this.telefone, this.situacao);
	}

	@Test
	public void getInstanceTest() {

		assertNotNull(response);
		assertEquals(this.id, response.getId());
		assertEquals(this.nome, response.getNome());
		assertEquals(this.cpfCnpj, response.getCpfCnpj());
		assertEquals(this.tipoDocumento, response.getTipoDocumento());
		assertEquals(this.email, response.getEmail());
		assertEquals(this.telefone, response.getTelefone());
		assertEquals(this.situacao, response.getSituacao());
	}	

	@Test
	public void getInstanceVaziaTest() {

		response = new InteressadoResponseDTO();

		assertNotNull(response);
		assertEquals(null, response.getId());
		assertEquals(null, response.getNome());
		assertEquals(null, response.getCpfCnpj());
		assertEquals(null, response.getTipoDocumento());
		assertEquals(null, response.getEmail());
		assertEquals(null, response.getTelefone());
		assertEquals(null, response.getSituacao());
	}

	@Test
	public void setAndGetAllFieldsTest() {

		response = new InteressadoResponseDTO();
		
		response.setId(this.id);
		response.setNome(this.nome);
		response.setCpfCnpj(this.cpfCnpj);
		response.setEmail(this.email);
		response.setTelefone(this.telefone);
		response.setTipoDocumento(this.tipoDocumento);
		response.setSituacao(this.situacao);

		assertEquals(this.id, response.getId());
		assertEquals(this.nome, response.getNome());
		assertEquals(this.cpfCnpj, response.getCpfCnpj());
		assertEquals(this.tipoDocumento, response.getTipoDocumento());
		assertEquals(this.email, response.getEmail());
		assertEquals(this.telefone, response.getTelefone());
		assertEquals(this.situacao, response.getSituacao());
	}

	@Test
	public void getEqualsTest() {

		InteressadoResponseDTO response2 = new InteressadoResponseDTO(this.id, this.nome, this.cpfCnpj, 
				this.tipoDocumento, this.email, this.telefone, this.situacao);

		assertNotNull(response);
		assertNotNull(response2);
		assertEquals(response, response2);
	}

	@Test
	public void getHashCodeTest() {

		InteressadoResponseDTO response2 = new InteressadoResponseDTO(this.id, this.nome, this.cpfCnpj, 
				this.tipoDocumento, this.email, this.telefone, this.situacao);

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
