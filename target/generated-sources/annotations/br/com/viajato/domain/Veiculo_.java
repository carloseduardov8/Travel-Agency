package br.com.viajato.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Veiculo.class)
public abstract class Veiculo_ {

	public static volatile SingularAttribute<Veiculo, String> tipo;
	public static volatile SingularAttribute<Veiculo, Locadora> locadora;
	public static volatile SetAttribute<Veiculo, Locacao> locacaos;
	public static volatile SingularAttribute<Veiculo, Integer> numPassageiros;
	public static volatile SingularAttribute<Veiculo, String> cor;
	public static volatile SingularAttribute<Veiculo, Integer> anoFabricacao;
	public static volatile SingularAttribute<Veiculo, Long> id;
	public static volatile SingularAttribute<Veiculo, String> fabricante;
	public static volatile SingularAttribute<Veiculo, Integer> anoModelo;
	public static volatile SingularAttribute<Veiculo, String> modelo;

}

