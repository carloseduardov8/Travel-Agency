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
 * A LinhaAerea.
 */
@Entity
@Table(name = "linha_aerea")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LinhaAerea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private String codigo;

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

    @OneToMany(mappedBy = "linhaAerea")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Voo> voos = new HashSet<>();
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

    public LinhaAerea nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public LinhaAerea codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public LinhaAerea logradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public LinhaAerea numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public LinhaAerea complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getCep() {
        return cep;
    }

    public LinhaAerea cep(Integer cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public LinhaAerea telefone(Integer telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Set<Voo> getVoos() {
        return voos;
    }

    public LinhaAerea voos(Set<Voo> voos) {
        this.voos = voos;
        return this;
    }

    public LinhaAerea addVoo(Voo voo) {
        this.voos.add(voo);
        voo.setLinhaAerea(this);
        return this;
    }

    public LinhaAerea removeVoo(Voo voo) {
        this.voos.remove(voo);
        voo.setLinhaAerea(null);
        return this;
    }

    public void setVoos(Set<Voo> voos) {
        this.voos = voos;
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
        LinhaAerea linhaAerea = (LinhaAerea) o;
        if (linhaAerea.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), linhaAerea.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LinhaAerea{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", logradouro='" + getLogradouro() + "'" +
            ", numero=" + getNumero() +
            ", complemento='" + getComplemento() + "'" +
            ", cep=" + getCep() +
            ", telefone=" + getTelefone() +
            "}";
    }
}
