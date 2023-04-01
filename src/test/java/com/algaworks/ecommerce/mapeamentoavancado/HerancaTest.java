package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class HerancaTest extends EntityManagerTest {

	@Test
	public void salvarCliente() {
		Cliente cliente = Cliente.builder()
				.nome("Fernanda Morais")
				.sexo(SexoCliente.FEMININO)
				.cpf("3333")
				.build();

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

		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setPedido(pedido);
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setNumeroCartao("123");

		entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getId());
	}
	
	@Test
	public void incluirPagamentoPedidoBoleto() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		PagamentoBoleto pagamentoBoleto = new PagamentoBoleto();
		pagamentoBoleto.setPedido(pedido);
		pagamentoBoleto.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoBoleto.setCodigoBarras("1234");
		
		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoBoleto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getId());
	}
}
