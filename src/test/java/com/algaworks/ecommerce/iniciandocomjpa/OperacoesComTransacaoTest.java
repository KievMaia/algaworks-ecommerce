package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest{

	@Test
	public void atualizarObjeto() {
		Produto produto = Produto.builder()
			.id(1)
			.nome("Kidle Paperwhite")
			.descricao("Conheça o novo Kidle")
			.preco(new BigDecimal("599"))
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
		Assert.assertEquals("Kidle Paperwhite", produto.getNome());
	}
	
	@Test
	public void removerObjeto() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear(); //Não é necessário na asserção para operação de remoção,
							   //pois o JPA já limpa da memória pois foi uma remoção.
		
		Produto produtoVerificacao = entityManager.find(Produto.class, 3);
		Assert.assertNull(produtoVerificacao);
	}
	
	@Test
	public void inserirOPrimeiroObjeto() {
		Produto produto = Produto.builder()
			.id(2)
			.nome("Câmera Canon")
			.descricao("A melhor definicao para suas fotos.")
			.preco(new BigDecimal("5000"))
			.build();
		

		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void abrirEFecharTransacao() {
//		Produto produto = new Produto(); //Somente para o método não mostrar erros.
		
		
		entityManager.getTransaction().begin();
		
//		entityManager.persist(produto);
//		entityManager.merge(produto);
//		entityManager.remove(produto);
		
		entityManager.getTransaction().commit();
	}
}
