package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Locadora.class)
public abstract class Locadora_ {

	public static volatile SetAttribute<Locadora, Veiculo> veiculos;
	public static volatile SingularAttribute<Locadora, Endereco> endereco;
	public static volatile SetAttribute<Locadora, Contrato> contratoes;
	public static volatile SingularAttribute<Locadora, String> nome;
	public static volatile SingularAttribute<Locadora, Long> id;
	public static volatile SetAttribute<Locadora, Telefone> telefones;

}

