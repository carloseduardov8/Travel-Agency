package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Locacao.class)
public abstract class Locacao_ {

	public static volatile SingularAttribute<Locacao, Veiculo> veiculo;
	public static volatile SingularAttribute<Locacao, Cliente> cliente;
	public static volatile SingularAttribute<Locacao, Integer> valor;
	public static volatile SingularAttribute<Locacao, String> inicio;
	public static volatile SingularAttribute<Locacao, Long> id;
	public static volatile SingularAttribute<Locacao, Integer> duracao;

}

