package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest{

	@Test
	public void impedirOperacaoComBancoDeDados() {
		Produto produto = entityManager.find(Produto.class, 1);
		entityManager.detach(produto);
		
		entityManager.getTransaction().begin();
		produto.setNome("Kindle Paperwhite 2ª Geração");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle", produtoVerificacao.getNome());
	}
	
	@Test
	public void mostrarDiferencaPersistMerge() {
		Produto produtoPersist = Produto.builder()
//			.id(5) Comentado pois estamos utilizando strategy IDENTITY
			.nome("Smartphone One Plus")
			.descricao("O processador mais rápido.")
			.preco(new BigDecimal("2000"))
			.build();
		

		entityManager.getTransaction().begin();
		entityManager.persist(produtoPersist);
		produtoPersist.setNome("Smartphone Two Plus");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
		Assert.assertNotNull(produtoVerificacaoPersist);
		
		Produto produtoMerge = Produto.builder()
				.nome("Notebook Dell")
				.descricao("O melhor da categoria.")
				.preco(new BigDecimal("2000"))
				.build();
			

			entityManager.getTransaction().begin();
			produtoMerge = entityManager.merge(produtoMerge);
			produtoMerge.setNome("Notebook Dell 2");
			entityManager.getTransaction().commit();
			
			entityManager.clear();
			
			Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
			Assert.assertNotNull(produtoVerificacaoMerge);
	}
	
	@Test
	public void inserirObjetoComMerge() {
		Produto produto = Produto.builder()
//			.id(2) Comentado pois estamos utilizando strategy IDENTITY
			.nome("Microfone Rode Videmic")
			.descricao("A melhor qualidade de som.")
			.preco(new BigDecimal("1000"))
			.build();
		

		entityManager.getTransaction().begin();
		Produto produtoPersistido = entityManager.merge(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produtoPersistido.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void atualizarObjetoGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		entityManager.getTransaction().begin();
		produto.setNome("Kindle Paperwhite 2ª Geração");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle Paperwhite 2ª Geração", produtoVerificacao.getNome());
	}
	
	@Test
	public void atualizarObjeto() {
		Produto produto = Produto.builder()
			.nome("Kidle Paperwhite")
			.descricao("Conheça o novo Kidle")
			.preco(new BigDecimal("599"))
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
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
//			.id(2) Comentado pois estamos utilizando strategy IDENTITY
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
