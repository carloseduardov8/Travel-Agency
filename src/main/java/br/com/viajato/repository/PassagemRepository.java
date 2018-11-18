package br.com.viajato.repository;

import br.com.viajato.domain.Passagem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Passagem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Long> {

    @Query("SELECT p FROM Passagem p WHERE p.compra.id = ?1")
    List<Passagem> getPassagensByCompraId(Long id);
}
