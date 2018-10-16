package br.com.viajato.repository;

import br.com.viajato.domain.Voo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Voo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VooRepository extends JpaRepository<Voo, Long> {

    @Query("SELECT v FROM Voo v WHERE v.partida = ?1 and v.origem = ?2 and v.destino= ?3")
    List<Voo> findVooByPartidaAndOrigemAndDestino(String partida, String origem, String destino);
}
