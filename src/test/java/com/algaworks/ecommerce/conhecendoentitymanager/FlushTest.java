package com.algaworks.ecommerce.conhecendoentitymanager;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class FlushTest extends EntityManagerTest {

	@Test(expected = Exception.class)
	public void chamarFlush() {
		try {
			entityManager.getTransaction().begin();

			Pedido pedido = entityManager.find(Pedido.class, 1);
			pedido.setStatus(StatusPedido.PAGO);

			entityManager.flush();

			if (pedido.getPagamento() == null) {
				throw new RuntimeException("Pedido ainda não foi pago");
			}
			
			// Uma consulta obriga o JPA a sincronizar o que ele tem na memória.
			PedidoStatus pedidoPago2 = entityManager.createQuery("SELECT new com.algaworks.ecommerce.conhecendoentitymanager.PedidoStatus(p.id, p.status) FROM Pedido p WHERE p.id = 1", PedidoStatus.class)
					.getSingleResult();

			Assert.assertEquals(pedido.getStatus(), pedidoPago2.getStatus());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
}
