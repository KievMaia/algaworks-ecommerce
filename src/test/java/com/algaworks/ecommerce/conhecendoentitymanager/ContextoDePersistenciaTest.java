package com.algaworks.ecommerce.conhecendoentitymanager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class ContextoDePersistenciaTest extends EntityManagerTest {

    @Test
    public void usarContextoPersistencia() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal("100.0"));

        Produto produto2 = Produto.builder()
                .nome("Caneca para café")
                .dataCriacao(LocalDateTime.now())
                .preco(new BigDecimal("10.0"))
                .descricao("Boa caneca para café")
                .build();
        entityManager.persist(produto2);

        Produto produto3 = Produto.builder()
                .nome("Caneca para chá")
                .dataCriacao(LocalDateTime.now())
                .preco(new BigDecimal("10.0"))
                .descricao("Boa caneca para chá").build();
        produto3 = entityManager.merge(produto3);

        entityManager.flush();

        produto3.setDescricao("Alterar descrição");

        entityManager.getTransaction().commit();
    }
}
