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
 * A Hotel.
 */
@Entity
@Table(name = "hotel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "nota", nullable = false)
    private Integer nota;

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

    @OneToMany(mappedBy = "hotel")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Quarto> quartos = new HashSet<>();
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

    public Hotel nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNota() {
        return nota;
    }

    public Hotel nota(Integer nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Hotel logradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public Hotel numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public Hotel complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getCep() {
        return cep;
    }

    public Hotel cep(Integer cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public Hotel telefone(Integer telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Set<Quarto> getQuartos() {
        return quartos;
    }

    public Hotel quartos(Set<Quarto> quartos) {
        this.quartos = quartos;
        return this;
    }

    public Hotel addQuarto(Quarto quarto) {
        this.quartos.add(quarto);
        quarto.setHotel(this);
        return this;
    }

    public Hotel removeQuarto(Quarto quarto) {
        this.quartos.remove(quarto);
        quarto.setHotel(null);
        return this;
    }

    public void setQuartos(Set<Quarto> quartos) {
        this.quartos = quartos;
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
        Hotel hotel = (Hotel) o;
        if (hotel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hotel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hotel{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", nota=" + getNota() +
            ", logradouro='" + getLogradouro() + "'" +
            ", numero=" + getNumero() +
            ", complemento='" + getComplemento() + "'" +
            ", cep=" + getCep() +
            ", telefone=" + getTelefone() +
            "}";
    }
}
