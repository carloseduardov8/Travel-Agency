package br.com.viajato.repository;

import br.com.viajato.domain.Locacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Locacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    @Query("SELECT l FROM Locacao l  WHERE l.compra.id = ?1")
    List<Locacao> getLocacoesByCompraId(Long id);

}
