package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contrato.class)
public abstract class Contrato_ {

	public static volatile SingularAttribute<Contrato, Locadora> locadora;
	public static volatile SingularAttribute<Contrato, Integer> numero;
	public static volatile SingularAttribute<Contrato, Seguradora> seguradora;
	public static volatile SingularAttribute<Contrato, Integer> valor;
	public static volatile SingularAttribute<Contrato, Long> id;
	public static volatile SetAttribute<Contrato, Cliente> clientes;
	public static volatile SingularAttribute<Contrato, String> descricao;

}

