package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Estoque;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class RelacionamentoManyToOneTest extends EntityManagerTest{

	@Test
	public void verificarRelacionamento() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = Pedido.builder()
			.status(StatusPedido.AGUARDANDO)
			.dataPedido(LocalDateTime.now())
			.cliente(cliente)
			.total(BigDecimal.TEN)
			.build();
		
		ItemPedido itemPedido = ItemPedido.builder()
			.pedido(pedido)
			.precoProduto(produto.getPreco())
			.produto(produto)
			.quantidade(1)
			.build();
			

		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
		Assert.assertNotNull(pedidoVerificacao.getCliente());
		
		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, 1);
		Assert.assertNotNull(itemPedidoVerificacao);
	}
	
	@Test
	public void verificarRelacionamentoEstoque() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		Estoque estoque = Estoque.builder()
			.produto(produto)
			.quantidade(20)
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(estoque);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Estoque estoqueVerificacao = entityManager.find(Estoque.class, 2);
		Assert.assertNotNull(estoqueVerificacao);
	}
}
