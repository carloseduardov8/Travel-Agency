package br.com.viajato.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Compra.
 */
@Entity
@Table(name = "compra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "compra")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Passagem> passagems = new HashSet<>();

    @OneToMany(mappedBy = "compra")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Locacao> locacaos = new HashSet<>();

    @OneToMany(mappedBy = "compra")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reserva> reservas = new HashSet<>();

    @OneToMany(mappedBy = "compra")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contrato> contratoes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Passagem> getPassagems() {
        return passagems;
    }

    public Compra passagems(Set<Passagem> passagems) {
        this.passagems = passagems;
        return this;
    }

    public Compra addPassagem(Passagem passagem) {
        this.passagems.add(passagem);
        passagem.setCompra(this);
        return this;
    }

    public Compra removePassagem(Passagem passagem) {
        this.passagems.remove(passagem);
        passagem.setCompra(null);
        return this;
    }

    public void setPassagems(Set<Passagem> passagems) {
        this.passagems = passagems;
    }

    public Set<Locacao> getLocacaos() {
        return locacaos;
    }

    public Compra locacaos(Set<Locacao> locacaos) {
        this.locacaos = locacaos;
        return this;
    }

    public Compra addLocacao(Locacao locacao) {
        this.locacaos.add(locacao);
        locacao.setCompra(this);
        return this;
    }

    public Compra removeLocacao(Locacao locacao) {
        this.locacaos.remove(locacao);
        locacao.setCompra(null);
        return this;
    }

    public void setLocacaos(Set<Locacao> locacaos) {
        this.locacaos = locacaos;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public Compra reservas(Set<Reserva> reservas) {
        this.reservas = reservas;
        return this;
    }

    public Compra addReserva(Reserva reserva) {
        this.reservas.add(reserva);
        reserva.setCompra(this);
        return this;
    }

    public Compra removeReserva(Reserva reserva) {
        this.reservas.remove(reserva);
        reserva.setCompra(null);
        return this;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Contrato> getContratoes() {
        return contratoes;
    }

    public Compra contratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
        return this;
    }

    public Compra addContrato(Contrato contrato) {
        this.contratoes.add(contrato);
        contrato.setCompra(this);
        return this;
    }

    public Compra removeContrato(Contrato contrato) {
        this.contratoes.remove(contrato);
        contrato.setCompra(null);
        return this;
    }

    public void setContratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
    }

    public User getUser() {
        return user;
    }

    public Compra user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        Compra compra = (Compra) o;
        if (compra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Compra{" +
            "id=" + getId() +
            "}";
    }
}
