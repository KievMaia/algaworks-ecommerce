package com.algaworks.ecommerce.mapeamentoavancado;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class ChaveCompostaTest extends EntityManagerTest{

	@Test
	public void salvarItem() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = Pedido.builder()
			.cliente(cliente)
			.dataCriacao(LocalDateTime.now())
			.status(StatusPedido.AGUARDANDO)
			.total(produto.getPreco())
			.build();
		
		entityManager.persist(pedido);
		
		entityManager.flush();
		
		ItemPedido itemPedido = ItemPedido.builder()
			//.pedidoId(pedido.getId()) IdClass
			//.produtoId(produto.getId()) IdClass
			.id(new ItemPedidoId())
			.pedido(pedido)
			.produto(produto)
			.precoProduto(produto.getPreco())
			.quantidade(1)
			.build();
		
		entityManager.persist(itemPedido);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
	}
	
	@Test
	public void buscarItem() {
		ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));
		
		Assert.assertNotNull(itemPedido);
	}
}
