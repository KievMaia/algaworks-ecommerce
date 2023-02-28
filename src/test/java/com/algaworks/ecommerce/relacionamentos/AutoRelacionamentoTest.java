package com.algaworks.ecommerce.relacionamentos;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class AutoRelacionamentoTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamento() {
		Categoria categoriaPai = Categoria.builder()
			.nome("Eletrônicos")
			.build();
		
		Categoria categoria = Categoria.builder()
			.nome("Celulares")
			.categoriaPai(categoriaPai)
			.build();
		
		entityManager.getTransaction().begin();
		entityManager.persist(categoriaPai);
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		
		Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
		Assert.assertNotNull(categoriaVerificacao.getCategoriaPai());
	}
}
