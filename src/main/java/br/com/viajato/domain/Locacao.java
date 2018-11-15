package br.com.viajato.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Locacao.
 */
@Entity
@Table(name = "locacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonIgnoreProperties("locacaos")
    private Veiculo veiculo;

    @ManyToOne
    @JsonIgnoreProperties("locacaos")
    private Compra compra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public Locacao dataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public Locacao dataFim(String dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Float getValor() {
        return valor;
    }

    public Locacao valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Locacao veiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        return this;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Compra getCompra() {
        return compra;
    }

    public Locacao compra(Compra compra) {
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
        Locacao locacao = (Locacao) o;
        if (locacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), locacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Locacao{" +
            "id=" + getId() +
            ", dataInicio='" + getDataInicio() + "'" +
            ", dataFim='" + getDataFim() + "'" +
            ", valor=" + getValor() +
            "}";
    }
}
