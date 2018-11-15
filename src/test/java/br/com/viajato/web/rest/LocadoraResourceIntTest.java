package br.com.viajato.web.rest;

import br.com.viajato.ViajatoApp;

import br.com.viajato.domain.Locadora;
import br.com.viajato.repository.LocadoraRepository;
import br.com.viajato.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.viajato.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LocadoraResource REST controller.
 *
 * @see LocadoraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViajatoApp.class)
public class LocadoraResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGEM = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEM = "BBBBBBBBBB";

    @Autowired
    private LocadoraRepository locadoraRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLocadoraMockMvc;

    private Locadora locadora;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocadoraResource locadoraResource = new LocadoraResource(locadoraRepository);
        this.restLocadoraMockMvc = MockMvcBuilders.standaloneSetup(locadoraResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Locadora createEntity(EntityManager em) {
        Locadora locadora = new Locadora()
            .nome(DEFAULT_NOME)
            .telefone(DEFAULT_TELEFONE)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .endereco(DEFAULT_ENDERECO)
            .imagem(DEFAULT_IMAGEM);
        return locadora;
    }

    @Before
    public void initTest() {
        locadora = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocadora() throws Exception {
        int databaseSizeBeforeCreate = locadoraRepository.findAll().size();

        // Create the Locadora
        restLocadoraMockMvc.perform(post("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isCreated());

        // Validate the Locadora in the database
        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeCreate + 1);
        Locadora testLocadora = locadoraList.get(locadoraList.size() - 1);
        assertThat(testLocadora.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testLocadora.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testLocadora.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testLocadora.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testLocadora.getEndereco()).isEqualTo(DEFAULT_ENDERECO);
        assertThat(testLocadora.getImagem()).isEqualTo(DEFAULT_IMAGEM);
    }

    @Test
    @Transactional
    public void createLocadoraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locadoraRepository.findAll().size();

        // Create the Locadora with an existing ID
        locadora.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocadoraMockMvc.perform(post("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isBadRequest());

        // Validate the Locadora in the database
        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = locadoraRepository.findAll().size();
        // set the field null
        locadora.setNome(null);

        // Create the Locadora, which fails.

        restLocadoraMockMvc.perform(post("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isBadRequest());

        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = locadoraRepository.findAll().size();
        // set the field null
        locadora.setTelefone(null);

        // Create the Locadora, which fails.

        restLocadoraMockMvc.perform(post("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isBadRequest());

        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCidadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = locadoraRepository.findAll().size();
        // set the field null
        locadora.setCidade(null);

        // Create the Locadora, which fails.

        restLocadoraMockMvc.perform(post("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isBadRequest());

        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = locadoraRepository.findAll().size();
        // set the field null
        locadora.setEstado(null);

        // Create the Locadora, which fails.

        restLocadoraMockMvc.perform(post("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isBadRequest());

        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnderecoIsRequired() throws Exception {
        int databaseSizeBeforeTest = locadoraRepository.findAll().size();
        // set the field null
        locadora.setEndereco(null);

        // Create the Locadora, which fails.

        restLocadoraMockMvc.perform(post("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isBadRequest());

        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLocadoras() throws Exception {
        // Initialize the database
        locadoraRepository.saveAndFlush(locadora);

        // Get all the locadoraList
        restLocadoraMockMvc.perform(get("/api/locadoras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locadora.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE.toString())))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO.toString())))
            .andExpect(jsonPath("$.[*].imagem").value(hasItem(DEFAULT_IMAGEM.toString())));
    }
    
    @Test
    @Transactional
    public void getLocadora() throws Exception {
        // Initialize the database
        locadoraRepository.saveAndFlush(locadora);

        // Get the locadora
        restLocadoraMockMvc.perform(get("/api/locadoras/{id}", locadora.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locadora.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE.toString()))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO.toString()))
            .andExpect(jsonPath("$.imagem").value(DEFAULT_IMAGEM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLocadora() throws Exception {
        // Get the locadora
        restLocadoraMockMvc.perform(get("/api/locadoras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocadora() throws Exception {
        // Initialize the database
        locadoraRepository.saveAndFlush(locadora);

        int databaseSizeBeforeUpdate = locadoraRepository.findAll().size();

        // Update the locadora
        Locadora updatedLocadora = locadoraRepository.findById(locadora.getId()).get();
        // Disconnect from session so that the updates on updatedLocadora are not directly saved in db
        em.detach(updatedLocadora);
        updatedLocadora
            .nome(UPDATED_NOME)
            .telefone(UPDATED_TELEFONE)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .endereco(UPDATED_ENDERECO)
            .imagem(UPDATED_IMAGEM);

        restLocadoraMockMvc.perform(put("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocadora)))
            .andExpect(status().isOk());

        // Validate the Locadora in the database
        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeUpdate);
        Locadora testLocadora = locadoraList.get(locadoraList.size() - 1);
        assertThat(testLocadora.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testLocadora.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testLocadora.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testLocadora.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testLocadora.getEndereco()).isEqualTo(UPDATED_ENDERECO);
        assertThat(testLocadora.getImagem()).isEqualTo(UPDATED_IMAGEM);
    }

    @Test
    @Transactional
    public void updateNonExistingLocadora() throws Exception {
        int databaseSizeBeforeUpdate = locadoraRepository.findAll().size();

        // Create the Locadora

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocadoraMockMvc.perform(put("/api/locadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locadora)))
            .andExpect(status().isBadRequest());

        // Validate the Locadora in the database
        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocadora() throws Exception {
        // Initialize the database
        locadoraRepository.saveAndFlush(locadora);

        int databaseSizeBeforeDelete = locadoraRepository.findAll().size();

        // Get the locadora
        restLocadoraMockMvc.perform(delete("/api/locadoras/{id}", locadora.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Locadora> locadoraList = locadoraRepository.findAll();
        assertThat(locadoraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Locadora.class);
        Locadora locadora1 = new Locadora();
        locadora1.setId(1L);
        Locadora locadora2 = new Locadora();
        locadora2.setId(locadora1.getId());
        assertThat(locadora1).isEqualTo(locadora2);
        locadora2.setId(2L);
        assertThat(locadora1).isNotEqualTo(locadora2);
        locadora1.setId(null);
        assertThat(locadora1).isNotEqualTo(locadora2);
    }
}
