package br.com.sigad.msc.interessado.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sigad.msc.interessado.entity.HistoricoInteressado;
import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.exception.SigadException;

/**
 * Interface que define os métodos do serviço para manter um {@link HistoricoInteressado}.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 2 de out. de 2020
 */
public interface HistoricoInteressadoService {
	
	/**
	 * Método que cria um histórico do interessado.
	 * @param historico - historico a ser criado
	 * @return HistoricoInteressado
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 2 de out. de 2020
	 */
	public HistoricoInteressado create(HistoricoInteressado historico) throws SigadException;
	
	/**
	 * Método que recupera a lista de histórico do interessado.
	 * @param interessado - interessado a ter o histórico recuperado
	 * @param pageable - paginação
	 * @return Page<HistoricoInteressado>
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 2 de out. de 2020
	 */
	public Page<HistoricoInteressado> list(Interessado interessado, Pageable pageable) throws SigadException;

}
