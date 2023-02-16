package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrimeiroCrudTest extends EntityManagerTest{

	@Test
	public void test1InserirRegistro() {
		Cliente cliente = Cliente.builder()
			.id(3)
			.nome("Kiev Maia")
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, 1);
		Assert.assertNotNull(clienteVerificado);
	}
	
	@Test
	public void test2BuscarPorIdentificador() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Fernando Medeiros", cliente.getNome());
	}
	
	@Test
	public void test3AtualizaRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		cliente.setNome("Kênia Rosa");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, 1);
		Assert.assertNotNull(clienteVerificado);
		Assert.assertEquals("Kênia Rosa", clienteVerificado.getNome());
	
	}
	
	@Test
	public void test4RemoverRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, 1);
		Assert.assertNull(clienteVerificado);
	}
}
