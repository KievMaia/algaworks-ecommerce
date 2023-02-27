package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapsIdTest extends EntityManagerTest{

	@Test
	public void inserirPagamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		NotaFiscal notaFiscal = NotaFiscal.builder()
			.pedido(pedido)
			.dataEmissao(new Date())
			.xml(carregarNotaFiscal())
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assert.assertNotNull(notaFiscalVerificacao);
		Assert.assertEquals(pedido.getId(), notaFiscalVerificacao.getId());
	}
	
	@Test
	public void inserirItemPedido( ) {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = Pedido.builder()
			.cliente(cliente)
			.dataCriacao(LocalDateTime.now())
			.status(StatusPedido.AGUARDANDO)
			.total(produto.getPreco())
			.build();
		
		ItemPedido itemPedido = ItemPedido.builder()
			.id(new ItemPedidoId())
			.pedido(pedido)
			.produto(produto)
			.precoProduto(produto.getPreco())
			.quantidade(1)
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		ItemPedido itemPedidoVerificacao = entityManager
				.find(ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		Assert.assertNotNull(itemPedidoVerificacao);
		
	}
	
	private static byte[] carregarNotaFiscal() {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
