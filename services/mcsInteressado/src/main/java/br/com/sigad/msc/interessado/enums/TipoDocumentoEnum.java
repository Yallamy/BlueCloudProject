package br.com.sigad.msc.interessado.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que apresenta os tipos de documentos possíveis do interessado
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoDocumentoEnum {

	CPF("CPF"),
	CNPJ("CNPJ");
	
	private String tipoDocumento;
	
	/**
	 * Método que retorna o tipo do documento do interessado
	 * @param tipoDocumento
	 * @return TipoDocumentoEnum
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public static TipoDocumentoEnum obterTipoDocumento(String tipoDocumento) {
		
		return Arrays.asList(values()).stream().filter(
				tr -> tr.tipoDocumento.equals(tipoDocumento)).findFirst().orElse(null);
	}
	
}
