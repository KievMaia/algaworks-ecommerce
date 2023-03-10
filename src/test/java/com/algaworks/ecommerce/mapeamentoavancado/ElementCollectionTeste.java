package com.algaworks.ecommerce.mapeamentoavancado;

import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;

public class ElementCollectionTeste extends EntityManagerTest{
	
	@Test
	public void aplicarTags() {
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setTags(Arrays.asList("ebook","livro-digital"));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
	}
	
	@Test
	public void aplicarAtributos() {
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setAtributos(Arrays.asList(Atributo.builder()
													.nome("tela")
													.valor("320x600")
													.build()));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertFalse(produtoVerificacao.getAtributos().isEmpty());
	}
	
	@Test
	public void aplicarContato() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		cliente.setContatos(Map.of("email", "fernando@email.com", "recados", "48 988556658"));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertEquals(clienteVerificacao.getContatos().get("email"), "fernando@email.com");
	}

}
