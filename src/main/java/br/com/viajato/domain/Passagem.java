package br.com.viajato.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Passagem.
 */
@Entity
@Table(name = "passagem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Passagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "assento", nullable = false)
    private String assento;

    @ManyToOne
    @JsonIgnoreProperties("passagems")
    private Voo voo;

    @ManyToOne
    @JsonIgnoreProperties("passagems")
    private Compra compra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssento() {
        return assento;
    }

    public Passagem assento(String assento) {
        this.assento = assento;
        return this;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public Voo getVoo() {
        return voo;
    }

    public Passagem voo(Voo voo) {
        this.voo = voo;
        return this;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Compra getCompra() {
        return compra;
    }

    public Passagem compra(Compra compra) {
        this.compra = compra;
        return this;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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
        Passagem passagem = (Passagem) o;
        if (passagem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), passagem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Passagem{" +
            "id=" + getId() +
            ", assento='" + getAssento() + "'" +
            "}";
    }
}
