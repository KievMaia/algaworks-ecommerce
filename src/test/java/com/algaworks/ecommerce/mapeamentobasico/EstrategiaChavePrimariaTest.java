package com.algaworks.ecommerce.mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class EstrategiaChavePrimariaTest extends EntityManagerTest{

	@Test
	public void testarEstrategiaAuto() {
		Categoria categoria = Categoria.builder()
			.nome("Eletrônicos")
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Categoria categoriaVerificacao = entityManager.find(Categoria.class, 1);
		Assert.assertNotNull(categoriaVerificacao);
	}
}
