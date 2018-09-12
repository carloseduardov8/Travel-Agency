package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, String> tipo;
	public static volatile SingularAttribute<Endereco, Cidade> cidade;
	public static volatile SingularAttribute<Endereco, String> complemento;
	public static volatile SingularAttribute<Endereco, Locadora> locadora;
	public static volatile SingularAttribute<Endereco, Integer> numero;
	public static volatile SingularAttribute<Endereco, String> logradouro;
	public static volatile SingularAttribute<Endereco, Seguradora> seguradora;
	public static volatile SingularAttribute<Endereco, Hotel> hotel;
	public static volatile SingularAttribute<Endereco, Long> id;

}

