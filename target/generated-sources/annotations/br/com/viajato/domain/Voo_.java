package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Voo.class)
public abstract class Voo_ {

	public static volatile SingularAttribute<Voo, LinhaAerea> linhaAerea;
	public static volatile SingularAttribute<Voo, Integer> numero;
	public static volatile SingularAttribute<Voo, String> chegada;
	public static volatile SetAttribute<Voo, Passagem> passagems;
	public static volatile SingularAttribute<Voo, Aeroporto> origem;
	public static volatile SingularAttribute<Voo, Long> id;
	public static volatile SingularAttribute<Voo, Aeroporto> destino;
	public static volatile SingularAttribute<Voo, String> partida;

}

