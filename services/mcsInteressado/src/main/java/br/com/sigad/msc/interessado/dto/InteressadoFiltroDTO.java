package br.com.sigad.msc.interessado.dto;

import java.io.Serializable;

import br.com.sigad.msc.interessado.util.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de request do filtro do interessado para transferência 
 * dos objetos pelo REST. 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = Constantes.INTERESSADO_FILTRO_DTO)
public class InteressadoFiltroDTO implements Serializable {

	private static final long serialVersionUID = -7048277477014890467L;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_FILTRO_NOME, position = 1)
	private String nome;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_FILTRO_CPF_CNPJ, position = 2)
	private String cpfCnpj;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_FILTRO_TIPO_DOCUMENTO, position = 3)
	private String tipoDocumento;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_FILTRO_EMAIL, position = 4)
	private String email;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_FILTRO_TELEFONE, position = 5)
	private String telefone;
	
	@ApiModelProperty(value = Constantes.INTERESSADO_FILTRO_SITUACAO, position = 6)
	private String situacao;

}
