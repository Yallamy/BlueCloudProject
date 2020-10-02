package br.com.sigad.msc.interessado.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;


/**
 * Classe que representa a customização das exceptions do sistema.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Getter
public class SigadException extends ResponseStatusException {

	private static final long serialVersionUID = 5101008253945611515L;
	
	private GenericValidacao erro;
	private List<String> parametros = new ArrayList<String>();

	/**
	 * Construtor da classe.
	 * @param erro
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public SigadException(GenericValidacao erro) {
		
		super(erro.getHttpStatus(), erro.getCodigoErro());
		
		this.erro = erro;
		this.parametros = new ArrayList<String>();
	}

	/**
	 * Construtor da classe.
	 * @param erro
	 * @param ex
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public SigadException(GenericValidacao erro, Exception ex) {
		
		super(erro.getHttpStatus(), erro.getCodigoErro(), ex);
		this.erro = erro;
		this.parametros = new ArrayList<String>();
	}

	/**
	 * Construtor da classe.
	 * @param erro
	 * @param params
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public SigadException(GenericValidacao erro, String... params) {
		
		super(erro.getHttpStatus(), erro.getCodigoErro());
		this.erro = erro;
		this.parametros = Arrays.asList(params);
	}

	/**
	 * Construtor da classe.
	 * @param erro
	 * @param ex
	 * @param params
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020 
	 */
	public SigadException(GenericValidacao erro, Exception ex, String... params) {
		
		super(erro.getHttpStatus(), erro.getCodigoErro(), ex);
		this.erro = erro;
		this.parametros = Arrays.asList(params);
	}

	/**
	 * Método que retorna a pilha de erros da excecução.
	 * @param t
	 * @return String - Uma String com a pilha de erros.
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public static final String getPilhaErro(Throwable t) {
		
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		t.printStackTrace(printWriter);
		
		return writer.toString();
	}
}
