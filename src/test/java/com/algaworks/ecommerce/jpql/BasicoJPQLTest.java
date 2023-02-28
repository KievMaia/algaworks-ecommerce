package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;

public class BasicoJPQLTest extends EntityManagerTest{

	@Test
	public void projetarOResultado() {
		String jpql = "select id, nome from Produto";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertTrue(lista.get(0).length == 2);
		
		lista.forEach(array -> System.out.println(array[0] + ", " + array[1]));
	}
	
	@Test
	public void selecionarUmAtributoParaRetorno() {
		String jqpl = "select p.nome from Produto p";
		
		TypedQuery<String> typedQuery = entityManager.createQuery(jqpl, String.class);
		List<String> lista = typedQuery.getResultList();
		Assert.assertTrue(String.class.equals(lista.get(0).getClass()));
		
		String jpqlCliente = "select p.cliente from Pedido p";
		TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
		List<Cliente> listaClientes = typedQueryCliente.getResultList();
		
		Assert.assertTrue(Cliente.class.equals(listaClientes.get(0).getClass()));
	}
	
	@Test
	public void buscarPorIdentificador() {
		
//		entityManager.find(Pedido.class, 1);
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);
		
		Pedido pedido = typedQuery.getSingleResult();
		Assert.assertNotNull(pedido);
		
		List<Pedido> lista =  typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void mostrarDiferencaQueries( ) {
		String jpql = "select p from Pedido p where p.id = 1";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		Pedido pedido1 = typedQuery.getSingleResult();
		Assert.assertNotNull(pedido1);
		
		Query query = entityManager.createQuery(jpql);
		Pedido pedido2 = (Pedido) query.getSingleResult();
		Assert.assertNotNull(pedido2);
		
//		List<Pedido> lista = query.getResultList();
//		Assert.assertFalse(lista.isEmpty());
	}
}