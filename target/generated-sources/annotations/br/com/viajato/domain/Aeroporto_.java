package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aeroporto.class)
public abstract class Aeroporto_ {

	public static volatile SingularAttribute<Aeroporto, String> codigo;
	public static volatile SingularAttribute<Aeroporto, Cidade> cidade;
	public static volatile SetAttribute<Aeroporto, Voo> vemDes;
	public static volatile SingularAttribute<Aeroporto, String> nome;
	public static volatile SingularAttribute<Aeroporto, Long> id;
	public static volatile SetAttribute<Aeroporto, Voo> vaiParas;

}

