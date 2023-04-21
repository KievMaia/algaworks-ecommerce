package com.algaworks.ecommerce.operacoesemcascata;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CascadeTypePersistTest extends EntityManagerTest {

    //@Test
    public void persistirPedidoComItens(){
        final var cliente = entityManager.find(Cliente.class, 1);
        final var produto = entityManager.find(Produto.class, 1);

        final var pedido = Pedido.builder()
                .dataCriacao(LocalDateTime.now())
                .cliente(cliente)
                .total(produto.getPreco())
                .status(StatusPedido.AGUARDANDO)
                .build();

        final var itemPedido = ItemPedido.builder()
                .id(new ItemPedidoId())
                .pedido(pedido)
                .produto(produto)
                .quantidade(1)
                .precoProduto(produto.getPreco())
                .build();
        pedido.setItens(Collections.singletonList(itemPedido)); //CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        final var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedido.getItens().isEmpty());
    }

    //@Test
    public void persistirPedidoComCliente(){
        final var cliente = Cliente.builder()
                .dataNascimento(LocalDate.of(1987, 11, 5))
                .sexo(SexoCliente.MASCULINO)
                .nome("Kiev Maia")
                .cpf("010.509-07")
                .build();

        final var pedido = Pedido.builder()
                .dataCriacao(LocalDateTime.now())
                .cliente(cliente) //CascadeType.PERSIST
                .total(BigDecimal.ZERO)
                .status(StatusPedido.AGUARDANDO)
                .build();

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        final var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
    }

   // @Test
    public void persistirItemPedidoComPedido(){
        final var cliente = entityManager.find(Cliente.class, 1);
        final var produto = entityManager.find(Produto.class, 1);

        final var pedido = Pedido.builder()
                .dataCriacao(LocalDateTime.now())
                .cliente(cliente)
                .total(produto.getPreco())
                .status(StatusPedido.AGUARDANDO)
                .build();

        final var itemPedido = ItemPedido.builder()
                .id(new ItemPedidoId())
                .pedido(pedido) //Não é necessário CascadeType.PERSIST, porque possui @MapsId.
                .produto(produto)
                .quantidade(1)
                .precoProduto(produto.getPreco())
                .build();

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();
    }

    //@Test
    public void persistPedidoComCategoria(){
        final var categoriaPai = entityManager.find(Categoria.class, 1);

        final var categoria = Categoria.builder()
                .nome("Som")
                .categoriaPai(categoriaPai)
                .build();

        final var produto = Produto.builder()
                .dataCriacao(LocalDateTime.now())
                .preco(BigDecimal.TEN)
                .nome("Fones de ouvido")
                .descricao("A melhor qualidade de som")
                .categorias(List.of(categoria))
                .build();

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
    }
}
