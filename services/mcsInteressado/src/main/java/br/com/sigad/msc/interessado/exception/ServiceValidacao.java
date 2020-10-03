package br.com.sigad.msc.interessado.exception;

import org.springframework.http.HttpStatus;

/**
 * Classe que implementa as validações genéricas do sistema.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public enum ServiceValidacao implements GenericValidacao{

	BAD_REQUEST("Campos obrigatórios não informados", HttpStatus.BAD_REQUEST),
	INTERESSADO_NAO_ENCONTRADO("Interessado não encontrado", HttpStatus.NOT_FOUND),
	INTERESSADO_INATIVO("Interessado inativo", HttpStatus.BAD_REQUEST),
	CPF_INVALIDO("O CPF informado é inválido", HttpStatus.BAD_REQUEST),
	CNPJ_INVALIDO("O CNPJ informado é inválido", HttpStatus.BAD_REQUEST),
	EMAIL_INVALIDO("O Email informado é inválido", HttpStatus.BAD_REQUEST),
	TELEFONE_INVALIDO("O telefone informado é inválido", HttpStatus.BAD_REQUEST),
	CPF_CNPJ_JA_CADASTRADO("O CPF/CNPJ informado já está cadastrado", HttpStatus.BAD_REQUEST);
	
	private String codigoErro;
	
	private HttpStatus httpStatus;

	/**
	 * Construtor privado da classe.
	 * @param codigoErro
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	private ServiceValidacao(String codigoErro, HttpStatus httpStatus) {
		this.codigoErro = codigoErro;
		this.httpStatus = httpStatus;
	}

	/**
	 * Método que retorna o código de erro referente a exception.
	 * @return String
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	@Override
	public String getCodigoErro() {
		return codigoErro;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
}
