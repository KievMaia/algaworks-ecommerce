package com.algaworks.ecommerce.mapeamentobasico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapeamentoObjetoEmbutido extends EntityManagerTest{

	@Test
	public void analisarMapeamentoObjetoEmbutido() {
		EnderecoEntregaPedido enderecoEntrega = EnderecoEntregaPedido.builder()
			.cep("0000000-00")
			.logradouro("Rua das Laranjeiras")
			.numero("123")
			.bairro("Centro")
			.cidade("Uberlândia")
			.estado("MG")
			.build();
		
		Pedido pedido = Pedido.builder()
			.id(1)
			.dataPedido(LocalDateTime.now())
			.status(StatusPedido.AGUARDANDO)
			.total(new BigDecimal("1000"))
			.entregaEntrega(enderecoEntrega)
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertNotNull(pedidoVerificacao.getEntregaEntrega());
		Assert.assertNotNull(pedidoVerificacao.getEntregaEntrega().getCep());
		
	}
}
