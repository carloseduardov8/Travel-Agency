package br.com.viajato.repository;

import br.com.viajato.domain.Quarto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Quarto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    @Query("SELECT q FROM Quarto q WHERE q.hotel.cidade LIKE ?1%")
    List<Quarto> getQuartosByCidade(String cidade);

}
