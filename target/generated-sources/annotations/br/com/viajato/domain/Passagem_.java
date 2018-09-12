package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Passagem.class)
public abstract class Passagem_ {

	public static volatile SingularAttribute<Passagem, Cliente> cliente;
	public static volatile SingularAttribute<Passagem, String> classe;
	public static volatile SingularAttribute<Passagem, Voo> voo;
	public static volatile SingularAttribute<Passagem, Integer> valor;
	public static volatile SingularAttribute<Passagem, Integer> cpf;
	public static volatile SingularAttribute<Passagem, String> nome;
	public static volatile SingularAttribute<Passagem, Long> id;

}

