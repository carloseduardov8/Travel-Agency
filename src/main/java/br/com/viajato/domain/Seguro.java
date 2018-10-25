package br.com.viajato.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Seguro.
 */
@Entity
@Table(name = "seguro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Seguro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "valor_por_pessoa", nullable = false)
    private Float valorPorPessoa;

    @OneToMany(mappedBy = "seguro")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contrato> contratoes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("seguros")
    private Seguradora seguradora;

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

    public Seguro nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Seguro descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValorPorPessoa() {
        return valorPorPessoa;
    }

    public Seguro valorPorPessoa(Float valorPorPessoa) {
        this.valorPorPessoa = valorPorPessoa;
        return this;
    }

    public void setValorPorPessoa(Float valorPorPessoa) {
        this.valorPorPessoa = valorPorPessoa;
    }

    public Set<Contrato> getContratoes() {
        return contratoes;
    }

    public Seguro contratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
        return this;
    }

    public Seguro addContrato(Contrato contrato) {
        this.contratoes.add(contrato);
        contrato.setSeguro(this);
        return this;
    }

    public Seguro removeContrato(Contrato contrato) {
        this.contratoes.remove(contrato);
        contrato.setSeguro(null);
        return this;
    }

    public void setContratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public Seguro seguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
        return this;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
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
        Seguro seguro = (Seguro) o;
        if (seguro.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), seguro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Seguro{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", valorPorPessoa=" + getValorPorPessoa() +
            "}";
    }
}
