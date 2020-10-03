package br.com.sigad.msc.interessado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigad.msc.interessado.entity.HistoricoInteressado;

/**
 * Repositório da entidade {@link HistoricoInteressado}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 2 de out. de 2020
 */
public interface HistoricoInteressadoRepository extends JpaRepository<HistoricoInteressado, Long> {

}
