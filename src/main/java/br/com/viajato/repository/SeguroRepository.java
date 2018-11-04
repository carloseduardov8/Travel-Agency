package br.com.viajato.repository;

import br.com.viajato.domain.Seguro;
import br.com.viajato.domain.Voo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Seguro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {
    @Query("SELECT s FROM Seguro s WHERE s.seguradora.cidade LIKE ?1%")
    List<Seguro> getSegurosByCidade(String cidade);

}
