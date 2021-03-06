package br.com.viajato.web.rest;

import br.com.viajato.ViajatoApp;

import br.com.viajato.domain.Passagem;
import br.com.viajato.repository.PassagemRepository;
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
 * Test class for the PassagemResource REST controller.
 *
 * @see PassagemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViajatoApp.class)
public class PassagemResourceIntTest {

    private static final String DEFAULT_ASSENTO = "AAAAAAAAAA";
    private static final String UPDATED_ASSENTO = "BBBBBBBBBB";

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPassagemMockMvc;

    private Passagem passagem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PassagemResource passagemResource = new PassagemResource(passagemRepository);
        this.restPassagemMockMvc = MockMvcBuilders.standaloneSetup(passagemResource)
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
    public static Passagem createEntity(EntityManager em) {
        Passagem passagem = new Passagem()
            .assento(DEFAULT_ASSENTO);
        return passagem;
    }

    @Before
    public void initTest() {
        passagem = createEntity(em);
    }

    @Test
    @Transactional
    public void createPassagem() throws Exception {
        int databaseSizeBeforeCreate = passagemRepository.findAll().size();

        // Create the Passagem
        restPassagemMockMvc.perform(post("/api/passagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passagem)))
            .andExpect(status().isCreated());

        // Validate the Passagem in the database
        List<Passagem> passagemList = passagemRepository.findAll();
        assertThat(passagemList).hasSize(databaseSizeBeforeCreate + 1);
        Passagem testPassagem = passagemList.get(passagemList.size() - 1);
        assertThat(testPassagem.getAssento()).isEqualTo(DEFAULT_ASSENTO);
    }

    @Test
    @Transactional
    public void createPassagemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = passagemRepository.findAll().size();

        // Create the Passagem with an existing ID
        passagem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPassagemMockMvc.perform(post("/api/passagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passagem)))
            .andExpect(status().isBadRequest());

        // Validate the Passagem in the database
        List<Passagem> passagemList = passagemRepository.findAll();
        assertThat(passagemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAssentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = passagemRepository.findAll().size();
        // set the field null
        passagem.setAssento(null);

        // Create the Passagem, which fails.

        restPassagemMockMvc.perform(post("/api/passagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passagem)))
            .andExpect(status().isBadRequest());

        List<Passagem> passagemList = passagemRepository.findAll();
        assertThat(passagemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPassagems() throws Exception {
        // Initialize the database
        passagemRepository.saveAndFlush(passagem);

        // Get all the passagemList
        restPassagemMockMvc.perform(get("/api/passagems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(passagem.getId().intValue())))
            .andExpect(jsonPath("$.[*].assento").value(hasItem(DEFAULT_ASSENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getPassagem() throws Exception {
        // Initialize the database
        passagemRepository.saveAndFlush(passagem);

        // Get the passagem
        restPassagemMockMvc.perform(get("/api/passagems/{id}", passagem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(passagem.getId().intValue()))
            .andExpect(jsonPath("$.assento").value(DEFAULT_ASSENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPassagem() throws Exception {
        // Get the passagem
        restPassagemMockMvc.perform(get("/api/passagems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePassagem() throws Exception {
        // Initialize the database
        passagemRepository.saveAndFlush(passagem);

        int databaseSizeBeforeUpdate = passagemRepository.findAll().size();

        // Update the passagem
        Passagem updatedPassagem = passagemRepository.findById(passagem.getId()).get();
        // Disconnect from session so that the updates on updatedPassagem are not directly saved in db
        em.detach(updatedPassagem);
        updatedPassagem
            .assento(UPDATED_ASSENTO);

        restPassagemMockMvc.perform(put("/api/passagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPassagem)))
            .andExpect(status().isOk());

        // Validate the Passagem in the database
        List<Passagem> passagemList = passagemRepository.findAll();
        assertThat(passagemList).hasSize(databaseSizeBeforeUpdate);
        Passagem testPassagem = passagemList.get(passagemList.size() - 1);
        assertThat(testPassagem.getAssento()).isEqualTo(UPDATED_ASSENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingPassagem() throws Exception {
        int databaseSizeBeforeUpdate = passagemRepository.findAll().size();

        // Create the Passagem

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPassagemMockMvc.perform(put("/api/passagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(passagem)))
            .andExpect(status().isBadRequest());

        // Validate the Passagem in the database
        List<Passagem> passagemList = passagemRepository.findAll();
        assertThat(passagemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePassagem() throws Exception {
        // Initialize the database
        passagemRepository.saveAndFlush(passagem);

        int databaseSizeBeforeDelete = passagemRepository.findAll().size();

        // Get the passagem
        restPassagemMockMvc.perform(delete("/api/passagems/{id}", passagem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Passagem> passagemList = passagemRepository.findAll();
        assertThat(passagemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Passagem.class);
        Passagem passagem1 = new Passagem();
        passagem1.setId(1L);
        Passagem passagem2 = new Passagem();
        passagem2.setId(passagem1.getId());
        assertThat(passagem1).isEqualTo(passagem2);
        passagem2.setId(2L);
        assertThat(passagem1).isNotEqualTo(passagem2);
        passagem1.setId(null);
        assertThat(passagem1).isNotEqualTo(passagem2);
    }
}
