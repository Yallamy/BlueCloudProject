package br.com.sigad.msc.interessado.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que apresenta os status possíveis do interessado
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {
	
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String status;
	
	/**
	 * Método que retorna a situação do interessado
	 * @param status
	 * @return StatusEnum
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public static StatusEnum obterStatus(String status) {
		
		return Arrays.asList(values()).stream().filter(
				tr -> tr.status.equals(status)).findFirst().orElse(null);
	}

}
