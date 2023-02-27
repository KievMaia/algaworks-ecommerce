package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
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

public class RelacionamentoOneToManyTest extends EntityManagerTest{

	@Test
	public void verificarRelacionamento() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = Pedido.builder()
			.status(StatusPedido.AGUARDANDO)
			.dataCriacao(LocalDateTime.now())
			.cliente(cliente)
			.total(BigDecimal.TEN)
			.build();
		
		ItemPedido itemPedido = ItemPedido.builder()
			.id(new ItemPedidoId())
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
		Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
		
	}
}
