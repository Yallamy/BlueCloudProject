package br.com.sigad.msc.interessado.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que apresenta os tipos de historico possíveis do interessado
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 2 de out. de 2020
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoHistoricoEnum {
	
	CREATE("Criar"),
	UPDATE("Atualizar"),
	DELETE("Apagar");
	
	private String tipoHistorico;
	
	/**
	 * Método que retorna o tipo de historico do interessado
	 * @param tipoHistorico
	 * @return TipoHistoricoEnum
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 2 de out. de 2020
	 */
	public static TipoHistoricoEnum obterTipoHistorico(String tipoHistorico) {
		
		return Arrays.asList(values()).stream().filter(
				tr -> tr.tipoHistorico.equals(tipoHistorico)).findFirst().orElse(null);
	}

}
