package br.com.sigad.msc.interessado.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sigad.msc.interessado.entity.HistoricoInteressado;
import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.exception.SigadException;

/**
 * Interface que define os métodos do serviço para manter um {@link Interessado}.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public interface InteressadoService {

	/**
	 * Método que cria um novo interessado.
	 * @param interessado - interessado a ser criado
	 * @return Interessado
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public Interessado create(Interessado interessado) throws SigadException;
	
	/**
	 * Método que atualiza um interessado.
	 * @param interessado - interessado a ser atualizado
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public void update(Interessado interessado) throws SigadException;
	
	/**
	 * Método que recupera um interessado.
	 * @param id - id do interessado
	 * @return Interessado
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public Interessado retrieve(Long id) throws SigadException;
	
	/**
	 * Método que recupera um interessado pelo CPF/CNPJ.
	 * @param cpfCnpj - CPF/CNPJ do interessado
	 * @return Interessado
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 2 de out. de 2020
	 */
	public Interessado retrieve(String cpfCnpj) throws SigadException;
	
	/**
	 * Método que deleta um interessado.
	 * @param interessado - interessado a ser deletado
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public void delete(Interessado interessado) throws SigadException;
	
	/**
	 * Método que lista um interessado.
	 * @param interessado - filtros a serem aplicados
	 * @param pageable - paginação
	 * @return Page<Interessado>
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public Page<Interessado> list(Interessado interessado, Pageable pageable) throws SigadException;
	
	/**
	 * Método que lista o histórico do interessado.
	 * @param interessado - interessado a ter o histórico recuperado
	 * @param pageable - paginação
	 * @return Page<HistoricoInteressado>
	 * @throws SigadException
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 2 de out. de 2020
	 */
	public Page<HistoricoInteressado> listHistoric(Interessado interessado, Pageable pageable) throws SigadException;
	
}
