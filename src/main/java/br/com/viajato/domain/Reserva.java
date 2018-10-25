package br.com.viajato.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Reserva.
 */
@Entity
@Table(name = "reserva")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "num_pessoas", nullable = false)
    private Integer numPessoas;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private String dataInicio;

    @NotNull
    @Column(name = "data_fim", nullable = false)
    private String dataFim;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Float valor;

    @ManyToOne
    @JsonIgnoreProperties("reservas")
    private Quarto quarto;

    @ManyToOne
    @JsonIgnoreProperties("reservas")
    private Compra compra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumPessoas() {
        return numPessoas;
    }

    public Reserva numPessoas(Integer numPessoas) {
        this.numPessoas = numPessoas;
        return this;
    }

    public void setNumPessoas(Integer numPessoas) {
        this.numPessoas = numPessoas;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public Reserva dataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public Reserva dataFim(String dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Float getValor() {
        return valor;
    }

    public Reserva valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Reserva quarto(Quarto quarto) {
        this.quarto = quarto;
        return this;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Compra getCompra() {
        return compra;
    }

    public Reserva compra(Compra compra) {
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
        Reserva reserva = (Reserva) o;
        if (reserva.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reserva.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reserva{" +
            "id=" + getId() +
            ", numPessoas=" + getNumPessoas() +
            ", dataInicio='" + getDataInicio() + "'" +
            ", dataFim='" + getDataFim() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
