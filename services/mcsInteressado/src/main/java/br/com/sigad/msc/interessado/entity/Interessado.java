package br.com.sigad.msc.interessado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.google.gson.annotations.Expose;

import br.com.sigad.msc.interessado.enums.StatusEnum;
import br.com.sigad.msc.interessado.enums.TipoDocumentoEnum;
import br.com.sigad.msc.interessado.util.Mensagem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe que representa a entidade interessado.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Table(name = "Interessado")
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
public class Interessado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_interessado")
	@Column(name = "id", nullable = false)
	@Expose
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 100)
	@NotEmpty(message = Mensagem.NAME_REQUIRED)
	@Expose
	private String nome;
	
	@Column(name = "cpfCnpj", nullable = false, length = 14, unique = true)
	@NotEmpty(message = Mensagem.CPF_CNPJ_REQUIRED)
	@Expose
	private String cpfCnpj;
	
	@Column(name = "tipoDocumento")
	@Enumerated(EnumType.STRING)
	@Expose
	private TipoDocumentoEnum tipoDocumento;
	
	@Column(name = "email", length = 100)
	@Expose
	private String email;
	
	@Column(name = "telefone", length = 100)
	@Expose
	private String telefone;
	
	@Column(name = "situacao")
	@Enumerated(EnumType.STRING)
	@Expose
	private StatusEnum situacao;

}
