package br.com.sigad.msc.interessado.dto;

import java.io.Serializable;

import br.com.sigad.msc.interessado.util.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de response do interessado para transferência 
 * dos objetos pelo REST.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = Constantes.INTERESSADO_REQUEST_DTO)
public class InteressadoResponseDTO implements Serializable {

	private static final long serialVersionUID = -8224014636968948773L;

	@ApiModelProperty(value = Constantes.INTERESSADO_RESPONSE_ID, position = 1)
	private Long id;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_RESPONSE_NOME, position = 2)
	private String nome;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_RESPONSE_CPF_CNPJ, position = 3)
	private String cpfCnpj;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_RESPONSE_TIPO_DOCUMENTO, position = 4)
	private String tipoDocumento;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_RESPONSE_EMAIL, position = 5)
	private String email;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_RESPONSE_TELEFONE, position = 6)
	private String telefone;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_RESPONSE_SITUACAO, position = 7)
	private String situacao;
}
