package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Quarto.class)
public abstract class Quarto_ {

	public static volatile SingularAttribute<Quarto, String> tipo;
	public static volatile SingularAttribute<Quarto, Integer> diaria;
	public static volatile SetAttribute<Quarto, Reserva> reservas;
	public static volatile SingularAttribute<Quarto, Integer> capacidade;
	public static volatile SingularAttribute<Quarto, Hotel> hotel;
	public static volatile SingularAttribute<Quarto, Long> id;
	public static volatile SingularAttribute<Quarto, String> descricao;

}

