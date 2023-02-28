package com.algaworks.ecommerce.mapeamentoavancado;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.PagamentoBoleto;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;

public class HerancaTest extends EntityManagerTest {

	@Test
	public void salvarCliente() {
		Cliente cliente = Cliente.builder().nome("Fernanda Morais").build();

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao.getId());
	}

	@Test
	public void buscarPagamentos() {
		List<PagamentoCartao> pagamentos = entityManager.createQuery("SELECT p FROM Pagamento p").getResultList();

		Assert.assertFalse(pagamentos.isEmpty());
	}

	@Test
	public void incluirPagamentoPedidoCartao() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setPedido(pedido);
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setNumeroCartao("123");
		
		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getId());
	}
	
	@Test
	public void incluirPagamentoPedidoBoleto() {
		Pedido pedido = entityManager.find(Pedido.class, 2);

		PagamentoBoleto pagamentoBoleto = new PagamentoBoleto();
		pagamentoBoleto.setPedido(pedido);
		pagamentoBoleto.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoBoleto.setCodigoBarras("123");
		
		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoBoleto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getId());
	}
}
