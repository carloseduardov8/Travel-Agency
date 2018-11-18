package br.com.viajato.repository;

import br.com.viajato.domain.Reserva;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Reserva entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {


    @Query("SELECT r FROM Reserva r WHERE r.compra.id = ?1")
    List<Reserva> getReservasByCompraId(Long id);

}
