package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

public class SalvandoArquivosTest extends EntityManagerTest{

	@Test
	public void salvarXmlNota() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		NotaFiscal notafiscal = NotaFiscal.builder()
			.pedido(pedido)
			.dataEmissao(new Date())
			.xml(carregarNotaFiscal())
			.build();
		
			
		entityManager.getTransaction().begin();
		entityManager.persist(notafiscal);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notafiscal.getId());
		Assert.assertNotNull(notaFiscalVerificacao.getXml());
		Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);
	
//		try {
//			OutputStream out = new FileOutputStream(Files.createFile(Paths.get(System.getProperty("user.home") + "/xml.xml")).toFile());
//			out.write(notaFiscalVerificacao.getXml());
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
	}
	
	private static byte[] carregarNotaFiscal() {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void salvarFotoProduto() {
		Produto produto = Produto.builder()
			.nome("Estampa")
			.descricao("Estampa Jiu Jitsu")
			.preco(new BigDecimal("5.00"))
			.foto(carregarFotoProduto())
			.build();
		
			
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoFotoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produto.getFoto());
		Assert.assertTrue(produto.getFoto().length > 0);
	
		try {
			OutputStream out = new FileOutputStream(Files.createFile(Paths.get(System.getProperty("user.home") + "/foto.jpg")).toFile());
			out.write(produtoFotoVerificacao.getFoto());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static byte[] carregarFotoProduto() {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream("/foto-produto.jpg").readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
