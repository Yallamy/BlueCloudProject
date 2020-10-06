package br.com.sigad.msc.interessado.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sigad.msc.interessado.util.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que encapsula os dados de response do histórico do interessado 
 * para transferência 
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 6 de out. de 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = Constantes.HISTORICO_RESPONSE_DTO)
public class HistoricoResponseDTO implements Serializable {
	
	private static final long serialVersionUID = -7164149153789288874L;

	@ApiModelProperty(value = Constantes.HISTORICO_RESPONSE_ID, position = 1)
	private Long id;
	
	@ApiModelProperty(value = Constantes.HISTORICO_RESPONSE_DATA_ALTERACAO, position = 2)
	private Date dtAlteracao;
	
	@ApiModelProperty(value = Constantes.HISTORICO_RESPONSE_TIPO, position = 3)
	private String tipoHistorico;
	
	@ApiModelProperty(value = Constantes.HISTORICO_RESPONSE_DADOS, position = 4)
	private String dadosJson;

}
