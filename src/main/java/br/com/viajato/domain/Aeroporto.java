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
 * A Aeroporto.
 */
@Entity
@Table(name = "aeroporto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aeroporto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @NotNull
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @NotNull
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "origem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Voo> vemDes = new HashSet<>();

    @OneToMany(mappedBy = "destino")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Voo> vaiParas = new HashSet<>();

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

    public Aeroporto nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Aeroporto telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public Aeroporto cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Aeroporto estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public Aeroporto endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<Voo> getVemDes() {
        return vemDes;
    }

    public Aeroporto vemDes(Set<Voo> voos) {
        this.vemDes = voos;
        return this;
    }

    public Aeroporto addVemDe(Voo voo) {
        this.vemDes.add(voo);
        voo.setOrigem(this);
        return this;
    }

    public Aeroporto removeVemDe(Voo voo) {
        this.vemDes.remove(voo);
        voo.setOrigem(null);
        return this;
    }

    public void setVemDes(Set<Voo> voos) {
        this.vemDes = voos;
    }

    public Set<Voo> getVaiParas() {
        return vaiParas;
    }

    public Aeroporto vaiParas(Set<Voo> voos) {
        this.vaiParas = voos;
        return this;
    }

    public Aeroporto addVaiPara(Voo voo) {
        this.vaiParas.add(voo);
        voo.setDestino(this);
        return this;
    }

    public Aeroporto removeVaiPara(Voo voo) {
        this.vaiParas.remove(voo);
        voo.setDestino(null);
        return this;
    }

    public void setVaiParas(Set<Voo> voos) {
        this.vaiParas = voos;
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
        Aeroporto aeroporto = (Aeroporto) o;
        if (aeroporto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aeroporto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aeroporto{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }
}
