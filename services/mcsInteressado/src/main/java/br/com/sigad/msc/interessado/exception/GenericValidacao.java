package br.com.sigad.msc.interessado.exception;

import org.springframework.http.HttpStatus;

/**
 * Interface que representa as validações genericas do sistema.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public interface GenericValidacao {
	
	/**
	 * Método que retorna o código de erro referente a exception.
	 * @return String
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public String getCodigoErro();
	
	/**
	 * Método que retorna o HttpStatus referente a Exception
	 * @return HttpStatus
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public HttpStatus getHttpStatus();

}
