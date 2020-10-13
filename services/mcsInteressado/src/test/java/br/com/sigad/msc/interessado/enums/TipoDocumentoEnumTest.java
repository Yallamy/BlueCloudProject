package br.com.sigad.msc.interessado.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Classe de teste que representa os cenários de testes da classe {@link TipoDocumentoEnum}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class TipoDocumentoEnumTest {
	
	@Test
	public void statusAtivoTest() {
		
		assertEquals(TipoDocumentoEnum.CPF, 
				TipoDocumentoEnum.obterTipoDocumento(TipoDocumentoEnum.CPF.getTipoDocumento()));
	}
	
	@Test
	public void statusInativoTest() {
		
		assertEquals(TipoDocumentoEnum.CNPJ, 
				TipoDocumentoEnum.obterTipoDocumento(TipoDocumentoEnum.CNPJ.getTipoDocumento()));
	}

}
