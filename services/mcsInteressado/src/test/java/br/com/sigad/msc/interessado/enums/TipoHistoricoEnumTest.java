package br.com.sigad.msc.interessado.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Classe de teste que representa os cenários de testes da classe {@link TipoHistoricoEnum}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class TipoHistoricoEnumTest {
	
	@Test
	public void tipoHistoricoCreateTest() {
		
		assertEquals(TipoHistoricoEnum.CREATE, 
				TipoHistoricoEnum.obterTipoHistorico(
						TipoHistoricoEnum.CREATE.getTipoHistorico()));
	}
	
	@Test
	public void tipoHistoricoUpdateTest() {
		
		assertEquals(TipoHistoricoEnum.UPDATE, 
				TipoHistoricoEnum.obterTipoHistorico(
						TipoHistoricoEnum.UPDATE.getTipoHistorico()));
	}
	
	@Test
	public void tipoHistoricoDeleteTest() {
		
		assertEquals(TipoHistoricoEnum.DELETE, 
				TipoHistoricoEnum.obterTipoHistorico(
						TipoHistoricoEnum.DELETE.getTipoHistorico()));
	}

}
