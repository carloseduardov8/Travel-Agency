package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Seguradora.class)
public abstract class Seguradora_ {

	public static volatile SingularAttribute<Seguradora, Endereco> endereco;
	public static volatile SetAttribute<Seguradora, Contrato> contratoes;
	public static volatile SingularAttribute<Seguradora, String> nome;
	public static volatile SingularAttribute<Seguradora, Long> id;
	public static volatile SetAttribute<Seguradora, Telefone> telefones;

}

