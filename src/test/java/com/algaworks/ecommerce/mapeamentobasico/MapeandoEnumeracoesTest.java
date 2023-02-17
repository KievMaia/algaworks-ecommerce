package com.algaworks.ecommerce.mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

public class MapeandoEnumeracoesTest extends EntityManagerTest{

	@Test
	public void testarEnum() {
		Cliente cliente = Cliente.builder()
//			.id(4) Comentado porque está sendo utilizado strategy IDENTITY
			.nome("José Mineiro")
			.sexo(SexoCliente.MASCULINO)
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
		Assert.assertNotNull(clienteVerificacao);
	}
}
