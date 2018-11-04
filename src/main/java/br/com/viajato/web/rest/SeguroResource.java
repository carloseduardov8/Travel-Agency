package br.com.viajato.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.viajato.domain.Seguro;
import br.com.viajato.repository.SeguroRepository;
import br.com.viajato.web.rest.errors.BadRequestAlertException;
import br.com.viajato.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Seguro.
 */
@RestController
@RequestMapping("/api")
public class SeguroResource {

    private final Logger log = LoggerFactory.getLogger(SeguroResource.class);

    private static final String ENTITY_NAME = "seguro";

    private final SeguroRepository seguroRepository;

    public SeguroResource(SeguroRepository seguroRepository) {
        this.seguroRepository = seguroRepository;
    }

    /**
     * POST  /seguros : Create a new seguro.
     *
     * @param seguro the seguro to create
     * @return the ResponseEntity with status 201 (Created) and with body the new seguro, or with status 400 (Bad Request) if the seguro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/seguros")
    @Timed
    public ResponseEntity<Seguro> createSeguro(@Valid @RequestBody Seguro seguro) throws URISyntaxException {
        log.debug("REST request to save Seguro : {}", seguro);
        if (seguro.getId() != null) {
            throw new BadRequestAlertException("A new seguro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Seguro result = seguroRepository.save(seguro);
        return ResponseEntity.created(new URI("/api/seguros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /seguros : Updates an existing seguro.
     *
     * @param seguro the seguro to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated seguro,
     * or with status 400 (Bad Request) if the seguro is not valid,
     * or with status 500 (Internal Server Error) if the seguro couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/seguros")
    @Timed
    public ResponseEntity<Seguro> updateSeguro(@Valid @RequestBody Seguro seguro) throws URISyntaxException {
        log.debug("REST request to update Seguro : {}", seguro);
        if (seguro.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Seguro result = seguroRepository.save(seguro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, seguro.getId().toString()))
            .body(result);
    }

    /**
     * GET  /seguros : get all the seguros.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of seguros in body
     */
    @GetMapping("/seguros")
    @Timed
    public List<Seguro> getAllSeguros() {
        log.debug("REST request to get all Seguros");
        return seguroRepository.findAll();
    }

    /**
     * GET  /seguros/:cidade : get seguros by cidade.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of seguros in body
     */
    @GetMapping("/seguros/cidade/{cidade}")
    @Timed
    public List<Seguro> getSegurosByCidade(@PathVariable String cidade) {
        log.debug("REST request to Seguros by cidade");
        return seguroRepository.getSegurosByCidade(cidade);
    }

    /**
     * GET  /seguros/:id : get the "id" seguro.
     *
     * @param id the id of the seguro to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the seguro, or with status 404 (Not Found)
     */
    @GetMapping("/seguros/{id}")
    @Timed
    public ResponseEntity<Seguro> getSeguro(@PathVariable Long id) {
        log.debug("REST request to get Seguro : {}", id);
        Optional<Seguro> seguro = seguroRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(seguro);
    }

    /**
     * DELETE  /seguros/:id : delete the "id" seguro.
     *
     * @param id the id of the seguro to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/seguros/{id}")
    @Timed
    public ResponseEntity<Void> deleteSeguro(@PathVariable Long id) {
        log.debug("REST request to delete Seguro : {}", id);

        seguroRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
