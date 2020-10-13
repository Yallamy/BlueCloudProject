package br.com.sigad.msc.interessado.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.sigad.msc.interessado.EntityGenericUtil;

/**
 * Classe de teste que representa os cenários de testes da classe {@link InteressadoRequestDTO}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class InteressadoRequestDTOTest {

	private Validator validator;

	private String nome;

	private String cpfCnpj;

	private String tipoDocumento;

	private String email;

	private String telefone;
	
	private InteressadoRequestDTO request;

	@BeforeEach
	public void setup() {

		this.nome = EntityGenericUtil.getString();
		this.cpfCnpj = EntityGenericUtil.getString();
		this.tipoDocumento = EntityGenericUtil.getString();
		this.email = EntityGenericUtil.getString();
		this.telefone = EntityGenericUtil.getString();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
		
		request = new InteressadoRequestDTO(this.nome, this.cpfCnpj, 
				this.tipoDocumento, this.email, this.telefone);
	}

	@Test
	public void getInstanceTest() {

		assertNotNull(request);
		assertEquals(this.nome, request.getNome());
		assertEquals(this.cpfCnpj, request.getCpfCnpj());
		assertEquals(this.tipoDocumento, request.getTipoDocumento());
		assertEquals(this.email, request.getEmail());
		assertEquals(this.telefone, request.getTelefone());
	}	

	@Test
	public void getInstanceVaziaTest() {

		request = new InteressadoRequestDTO();

		assertNotNull(request);
		assertEquals(null, request.getNome());
		assertEquals(null, request.getCpfCnpj());
		assertEquals(null, request.getTipoDocumento());
		assertEquals(null, request.getEmail());
		assertEquals(null, request.getTelefone());
	}

	@Test
	public void getInstanceTestNomeNull() {
		
		request.setNome(null);

		assertNotNull(request);
		Set<ConstraintViolation<InteressadoRequestDTO>> violations = 
				validator.validate(request);
		assertTrue(violations.size() == 1);
	}

	@Test
	public void getInstanceTestCpfCnpjNull() {

		request.setCpfCnpj(null);

		assertNotNull(request);
		Set<ConstraintViolation<InteressadoRequestDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
	}

	@Test
	public void getInstanceTestTipoDocumentoNull() {

		request.setTipoDocumento(null);

		assertNotNull(request);
		Set<ConstraintViolation<InteressadoRequestDTO>> violations = validator.validate(request);
		assertTrue(violations.size() == 1);
	}

	@Test
	public void setAndGetAllFieldsTest() {

		request = new InteressadoRequestDTO();
		
		request.setNome(this.nome);
		request.setCpfCnpj(this.cpfCnpj);
		request.setEmail(this.email);
		request.setTelefone(this.telefone);
		request.setTipoDocumento(this.tipoDocumento);

		assertEquals(this.nome, request.getNome());
		assertEquals(this.cpfCnpj, request.getCpfCnpj());
		assertEquals(this.tipoDocumento, request.getTipoDocumento());
		assertEquals(this.email, request.getEmail());
		assertEquals(this.telefone, request.getTelefone());
	}

	@Test
	public void getEqualsTest() {

		InteressadoRequestDTO request2 = new InteressadoRequestDTO(this.nome, this.cpfCnpj, 
				this.tipoDocumento, this.email, this.telefone);

		assertNotNull(request);
		assertNotNull(request2);
		assertEquals(request, request2);
	}

	@Test
	public void getHashCodeTest() {

		InteressadoRequestDTO request2 = new InteressadoRequestDTO(this.nome, this.cpfCnpj, 
				this.tipoDocumento, this.email, this.telefone);

		assertNotNull(request);
		assertNotNull(request2);
		assertEquals(request.hashCode(), request2.hashCode());
	}

	@Test
	public void getToStringTest() {

		assertNotNull(request);
		assertNotNull(request.toString());
	}
}
