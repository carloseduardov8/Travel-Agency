package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Hotel.class)
public abstract class Hotel_ {

	public static volatile SetAttribute<Hotel, Quarto> quartos;
	public static volatile SingularAttribute<Hotel, Endereco> endereco;
	public static volatile SingularAttribute<Hotel, String> nome;
	public static volatile SingularAttribute<Hotel, Long> id;
	public static volatile SingularAttribute<Hotel, Integer> nota;
	public static volatile SetAttribute<Hotel, Telefone> telefones;

}

