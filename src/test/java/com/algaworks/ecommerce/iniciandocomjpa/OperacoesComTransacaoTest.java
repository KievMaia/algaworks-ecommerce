package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest{

	@Test
	public void abrirEFecharTransacao() {
		Produto produto = new Produto(); //Somente para o método não mostrar erros.
		
		
		entityManager.getTransaction().begin();
		
//		entityManager.persist(produto);
//		entityManager.merge(produto);
//		entityManager.remove(produto);
		
		entityManager.getTransaction().commit();
	}
}
