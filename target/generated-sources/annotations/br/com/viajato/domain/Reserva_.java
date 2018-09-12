package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reserva.class)
public abstract class Reserva_ {

	public static volatile SingularAttribute<Reserva, Cliente> cliente;
	public static volatile SingularAttribute<Reserva, Quarto> quarto;
	public static volatile SingularAttribute<Reserva, Integer> valor;
	public static volatile SingularAttribute<Reserva, String> inicio;
	public static volatile SingularAttribute<Reserva, Long> id;
	public static volatile SingularAttribute<Reserva, Integer> duracao;
	public static volatile SingularAttribute<Reserva, Integer> numPessoas;

}

