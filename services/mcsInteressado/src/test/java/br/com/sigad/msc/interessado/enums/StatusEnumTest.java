package br.com.sigad.msc.interessado.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Classe de teste que representa os cenários de testes da classe {@link StatusEnum}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class StatusEnumTest {
	
	@Test
	public void statusAtivoTest() {
		
		assertEquals(StatusEnum.ATIVO, StatusEnum.obterStatus(StatusEnum.ATIVO.getStatus()));
	}
	
	@Test
	public void statusInativoTest() {
		
		assertEquals(StatusEnum.INATIVO, StatusEnum.obterStatus(StatusEnum.INATIVO.getStatus()));
	}

}
