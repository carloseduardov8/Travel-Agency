package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Telefone.class)
public abstract class Telefone_ {

	public static volatile SingularAttribute<Telefone, LinhaAerea> linhaAerea;
	public static volatile SingularAttribute<Telefone, Locadora> locadora;
	public static volatile SingularAttribute<Telefone, Integer> numero;
	public static volatile SingularAttribute<Telefone, Integer> ddd;
	public static volatile SingularAttribute<Telefone, Seguradora> seguradora;
	public static volatile SingularAttribute<Telefone, Hotel> hotel;
	public static volatile SingularAttribute<Telefone, Long> id;

}

