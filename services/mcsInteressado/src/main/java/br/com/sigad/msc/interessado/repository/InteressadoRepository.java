package br.com.sigad.msc.interessado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigad.msc.interessado.entity.Interessado;

/**
 * Repositório da entidade {@link Interessado}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Repository
public interface InteressadoRepository extends JpaRepository<Interessado, Long> {
	
	/**
	 * Método que recupera um interessado por CPF/CNPJ.
	 * @param cpfCnpj - CPF ou CNPJ do interessado
	 * @return Interessado
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 2 de out. de 2020
	 */
	public Interessado findByCpfCnpj(String cpfCnpj);

}
