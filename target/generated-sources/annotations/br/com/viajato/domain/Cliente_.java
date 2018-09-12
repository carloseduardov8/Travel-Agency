package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, String> senha;
	public static volatile SetAttribute<Cliente, Locacao> locacaos;
	public static volatile SetAttribute<Cliente, Reserva> reservas;
	public static volatile SetAttribute<Cliente, Passagem> passagems;
	public static volatile SingularAttribute<Cliente, Integer> cpf;
	public static volatile SingularAttribute<Cliente, Contrato> contrato;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> email;

}

