package com.algaworks.ecommerce.jpql;

import java.awt.RadialGradientPaint;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pagamento;
import com.algaworks.ecommerce.model.Pedido;

public class JoinTest extends EntityManagerTest{

	@Test
	public void fazerJoin() { // No JOIN as duas tabelas ou mais tem que ter ocorrências.
		String jpql = "select p, pag from Pedido p join p.pagamento pag";
		
//		String jpqlObj = "select p, pag from Pedido p join p.pagamento pag where pag.status = 'PROCESSANDO'";
		
//		String jpqlObjLista = "select p from Pedido p join p.itens i where i.precoProduto > 10";
		
//		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
//		List<Pedido> lista = typedQuery.getResultList();
//		
//		Assert.assertTrue(lista.size() == 1);
		
		TypedQuery<Object[]> typedQueryObj = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> listaObj = typedQueryObj.getResultList();
		
		Object[] objects = listaObj.get(0);
		Pedido pedido = (Pedido) objects[0];
		Pagamento pagamento = (Pagamento) objects[1];
		
		pedido.getCliente().getNome();
	}
}
