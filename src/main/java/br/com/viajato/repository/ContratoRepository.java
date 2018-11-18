package br.com.viajato.repository;

import br.com.viajato.domain.Contrato;
import br.com.viajato.domain.Passagem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Contrato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    @Query("SELECT c FROM Contrato c WHERE c.compra.id = ?1")
    List<Contrato> getContratosByCompraId(Long id);
}
