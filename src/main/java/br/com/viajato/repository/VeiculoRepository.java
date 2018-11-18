package br.com.viajato.repository;

import br.com.viajato.domain.Veiculo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Veiculo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.locadora.cidade LIKE ?1%")
    List<Veiculo> getVeiculosByCidade(String cidade);
}
