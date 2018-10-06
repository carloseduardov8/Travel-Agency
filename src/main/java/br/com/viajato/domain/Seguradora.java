package br.com.viajato.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Seguradora.
 */
@Entity
@Table(name = "seguradora")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Seguradora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @NotNull
    @Column(name = "cep", nullable = false)
    private Integer cep;

    @NotNull
    @Column(name = "telefone", nullable = false)
    private Integer telefone;

    @OneToMany(mappedBy = "seguradora")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contrato> contratoes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Seguradora nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Seguradora logradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public Seguradora numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public Seguradora complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getCep() {
        return cep;
    }

    public Seguradora cep(Integer cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public Seguradora telefone(Integer telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Set<Contrato> getContratoes() {
        return contratoes;
    }

    public Seguradora contratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
        return this;
    }

    public Seguradora addContrato(Contrato contrato) {
        this.contratoes.add(contrato);
        contrato.setSeguradora(this);
        return this;
    }

    public Seguradora removeContrato(Contrato contrato) {
        this.contratoes.remove(contrato);
        contrato.setSeguradora(null);
        return this;
    }

    public void setContratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seguradora seguradora = (Seguradora) o;
        if (seguradora.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), seguradora.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Seguradora{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", logradouro='" + getLogradouro() + "'" +
            ", numero=" + getNumero() +
            ", complemento='" + getComplemento() + "'" +
            ", cep=" + getCep() +
            ", telefone=" + getTelefone() +
            "}";
    }
}
