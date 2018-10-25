package br.com.viajato.web.rest;

import br.com.viajato.ViajatoApp;

import br.com.viajato.domain.Seguro;
import br.com.viajato.repository.SeguroRepository;
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
 * Test class for the SeguroResource REST controller.
 *
 * @see SeguroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViajatoApp.class)
public class SeguroResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Float DEFAULT_VALOR_POR_PESSOA = 1F;
    private static final Float UPDATED_VALOR_POR_PESSOA = 2F;

    @Autowired
    private SeguroRepository seguroRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSeguroMockMvc;

    private Seguro seguro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SeguroResource seguroResource = new SeguroResource(seguroRepository);
        this.restSeguroMockMvc = MockMvcBuilders.standaloneSetup(seguroResource)
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
    public static Seguro createEntity(EntityManager em) {
        Seguro seguro = new Seguro()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO)
            .valorPorPessoa(DEFAULT_VALOR_POR_PESSOA);
        return seguro;
    }

    @Before
    public void initTest() {
        seguro = createEntity(em);
    }

    @Test
    @Transactional
    public void createSeguro() throws Exception {
        int databaseSizeBeforeCreate = seguroRepository.findAll().size();

        // Create the Seguro
        restSeguroMockMvc.perform(post("/api/seguros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seguro)))
            .andExpect(status().isCreated());

        // Validate the Seguro in the database
        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeCreate + 1);
        Seguro testSeguro = seguroList.get(seguroList.size() - 1);
        assertThat(testSeguro.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testSeguro.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSeguro.getValorPorPessoa()).isEqualTo(DEFAULT_VALOR_POR_PESSOA);
    }

    @Test
    @Transactional
    public void createSeguroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = seguroRepository.findAll().size();

        // Create the Seguro with an existing ID
        seguro.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSeguroMockMvc.perform(post("/api/seguros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seguro)))
            .andExpect(status().isBadRequest());

        // Validate the Seguro in the database
        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = seguroRepository.findAll().size();
        // set the field null
        seguro.setNome(null);

        // Create the Seguro, which fails.

        restSeguroMockMvc.perform(post("/api/seguros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seguro)))
            .andExpect(status().isBadRequest());

        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = seguroRepository.findAll().size();
        // set the field null
        seguro.setDescricao(null);

        // Create the Seguro, which fails.

        restSeguroMockMvc.perform(post("/api/seguros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seguro)))
            .andExpect(status().isBadRequest());

        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorPorPessoaIsRequired() throws Exception {
        int databaseSizeBeforeTest = seguroRepository.findAll().size();
        // set the field null
        seguro.setValorPorPessoa(null);

        // Create the Seguro, which fails.

        restSeguroMockMvc.perform(post("/api/seguros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seguro)))
            .andExpect(status().isBadRequest());

        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSeguros() throws Exception {
        // Initialize the database
        seguroRepository.saveAndFlush(seguro);

        // Get all the seguroList
        restSeguroMockMvc.perform(get("/api/seguros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(seguro.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].valorPorPessoa").value(hasItem(DEFAULT_VALOR_POR_PESSOA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getSeguro() throws Exception {
        // Initialize the database
        seguroRepository.saveAndFlush(seguro);

        // Get the seguro
        restSeguroMockMvc.perform(get("/api/seguros/{id}", seguro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(seguro.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.valorPorPessoa").value(DEFAULT_VALOR_POR_PESSOA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSeguro() throws Exception {
        // Get the seguro
        restSeguroMockMvc.perform(get("/api/seguros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSeguro() throws Exception {
        // Initialize the database
        seguroRepository.saveAndFlush(seguro);

        int databaseSizeBeforeUpdate = seguroRepository.findAll().size();

        // Update the seguro
        Seguro updatedSeguro = seguroRepository.findById(seguro.getId()).get();
        // Disconnect from session so that the updates on updatedSeguro are not directly saved in db
        em.detach(updatedSeguro);
        updatedSeguro
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO)
            .valorPorPessoa(UPDATED_VALOR_POR_PESSOA);

        restSeguroMockMvc.perform(put("/api/seguros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSeguro)))
            .andExpect(status().isOk());

        // Validate the Seguro in the database
        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeUpdate);
        Seguro testSeguro = seguroList.get(seguroList.size() - 1);
        assertThat(testSeguro.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testSeguro.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSeguro.getValorPorPessoa()).isEqualTo(UPDATED_VALOR_POR_PESSOA);
    }

    @Test
    @Transactional
    public void updateNonExistingSeguro() throws Exception {
        int databaseSizeBeforeUpdate = seguroRepository.findAll().size();

        // Create the Seguro

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSeguroMockMvc.perform(put("/api/seguros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(seguro)))
            .andExpect(status().isBadRequest());

        // Validate the Seguro in the database
        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSeguro() throws Exception {
        // Initialize the database
        seguroRepository.saveAndFlush(seguro);

        int databaseSizeBeforeDelete = seguroRepository.findAll().size();

        // Get the seguro
        restSeguroMockMvc.perform(delete("/api/seguros/{id}", seguro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Seguro> seguroList = seguroRepository.findAll();
        assertThat(seguroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Seguro.class);
        Seguro seguro1 = new Seguro();
        seguro1.setId(1L);
        Seguro seguro2 = new Seguro();
        seguro2.setId(seguro1.getId());
        assertThat(seguro1).isEqualTo(seguro2);
        seguro2.setId(2L);
        assertThat(seguro1).isNotEqualTo(seguro2);
        seguro1.setId(null);
        assertThat(seguro1).isNotEqualTo(seguro2);
    }
}
