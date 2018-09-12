package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LinhaAerea.class)
public abstract class LinhaAerea_ {

	public static volatile SingularAttribute<LinhaAerea, String> codigo;
	public static volatile SingularAttribute<LinhaAerea, String> nome;
	public static volatile SetAttribute<LinhaAerea, Voo> voos;
	public static volatile SingularAttribute<LinhaAerea, Long> id;
	public static volatile SetAttribute<LinhaAerea, Telefone> telefones;

}

