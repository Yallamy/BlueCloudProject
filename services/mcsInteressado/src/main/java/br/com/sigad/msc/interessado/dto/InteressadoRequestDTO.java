package br.com.sigad.msc.interessado.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.sigad.msc.interessado.util.Constantes;
import br.com.sigad.msc.interessado.util.Mensagem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de request do interessado para transferência 
 * dos objetos pelo REST. 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = Constantes.INTERESSADO_REQUEST_DTO)
public class InteressadoRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 3156380711912512767L;

	@ApiModelProperty(value = Constantes.INTERESSADO_REQUEST_NOME, position = 1)
	@NotEmpty(message = Mensagem.NAME_REQUIRED)
	private String nome;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_REQUEST_CPF_CNPJ, position = 2)
	@NotEmpty(message = Mensagem.CPF_CNPJ_REQUIRED)
	private String cpfCnpj;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_REQUEST_TIPO_DOCUMENTO, position = 3)
	@NotEmpty(message = Mensagem.TIPO_DOCUMENTO_REQUIRED)
	private String tipoDocumento;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_REQUEST_EMAIL, position = 4)
	private String email;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_REQUEST_TELEFONE, position = 5)
	private String telefone;
}
