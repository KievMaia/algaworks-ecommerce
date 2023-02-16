package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

public class PrimeiroCrudTest extends EntityManagerTest{

	@Test
	public void inserirRegistro() {
		Cliente cliente = Cliente.builder()
			.id(3)
			.nome("Kiev Maia")
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificado);
	}
	
	@Test
	public void buscarPorIdentificador() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Fernando Medeiros", cliente.getNome());
	}
	
	@Test
	public void atualizaRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 2);
		cliente.setNome("Kênia Rosa");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, 2);
		Assert.assertNotNull(clienteVerificado);
		Assert.assertEquals("Kênia Rosa", clienteVerificado.getNome());
	
	}
	
	@Test
	public void removerRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 2);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, 2);
		Assert.assertNull(clienteVerificado);
	}
}
