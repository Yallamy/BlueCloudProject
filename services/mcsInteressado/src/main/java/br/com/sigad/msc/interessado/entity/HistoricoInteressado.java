package br.com.sigad.msc.interessado.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import br.com.sigad.msc.interessado.enums.TipoHistoricoEnum;
import br.com.sigad.msc.interessado.util.Mensagem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe que representa a entidade HistoricoInteressado.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 2 de out. de 2020
 */
@Table(name = "HistoricoPessoa")
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
public class HistoricoInteressado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_HistInteressado")
	@Column(name = "id", nullable = false)
	@Expose
	private Long id;
	
	@Column(name = "dtAlteracao", nullable = false)
	@NotNull(message = Mensagem.DT_ALTERACAO_REQUIRED)
	@Expose
	private Date dtAlteracao;
	
	@Column(name = "tipoHistorico", nullable = false, length = 30)
	@NotNull(message = Mensagem.TIPO_HISTORICO_REQUIRED)
	@Expose
	private TipoHistoricoEnum tipoHistorico;
	
	@Column(name = "dadosJson", length = 3000)
	@NotNull(message = Mensagem.DADOS_HISTORICO_REQUIRED)
	private String dadosJson;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idInteressado", nullable = false)
	@NotNull(message = Mensagem.INTERESSADO_REQUIRED)
	@JsonIgnore
	private Interessado interessado;
}
