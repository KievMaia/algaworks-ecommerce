package com.algaworks.ecommerce.relacionamentos;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Estoque;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPagamento;
import com.algaworks.ecommerce.model.StatusPedido;

public class RelacionamentoManyToOneTest extends EntityManagerTest{

	@Test
	public void verificarRelacionamento() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = Pedido.builder()
			.status(StatusPedido.AGUARDANDO)
			.dataCriacao(LocalDateTime.now())
			.cliente(cliente)
			.build();
		
		PagamentoCartao pagamentoCartao = PagamentoCartao.builder()
			.id(pedido.getId())
			.numero("123456")
			.status(StatusPagamento.RECEBIDO)
			.pedido(pedido)
			.build();
		
		pedido.setPagamento(pagamentoCartao);
		
		
		ItemPedido itemPedido = ItemPedido.builder()
			//.pedidoId(pedido.getId()) IdClass
			//.produtoId(produto.getId()) IdClass
			.id(new ItemPedidoId())
			.pedido(pedido)
			.precoProduto(produto.getPreco())
			.produto(produto)
			.quantidade(2)
			.build();
		
		ItemPedido itemPedido2 = ItemPedido.builder()
				//.pedidoId(pedido.getId()) IdClass
				//.produtoId(produto.getId()) IdClass
				.id(new ItemPedidoId())
				.pedido(pedido)
				.precoProduto(produto.getPreco())
				.produto(produto)
				.quantidade(2)
				.build();
		
		pedido.setItens(List.of(itemPedido, itemPedido2));
		pedido.calcularTotal();

		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.persist(pagamentoCartao);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		
		ItemPedido itemPedidoIdVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		Assert.assertNotNull(itemPedidoIdVerificacao);
		Assert.assertNotNull(itemPedidoIdVerificacao.getPedido());
		Assert.assertNotNull(itemPedidoIdVerificacao.getProduto());
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
