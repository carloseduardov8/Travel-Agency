package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cidade.class)
public abstract class Cidade_ {

	public static volatile SingularAttribute<Cidade, Estado> estado;
	public static volatile SetAttribute<Cidade, Endereco> enderecos;
	public static volatile SingularAttribute<Cidade, String> nome;
	public static volatile SingularAttribute<Cidade, Long> id;
	public static volatile SetAttribute<Cidade, Aeroporto> aeroportos;

}

