package br.com.viajato.repository;

import br.com.viajato.domain.Compra;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Compra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("select compra from Compra compra where compra.user.login = ?#{principal.username}")
    List<Compra> findByUserIsCurrentUser();

}
