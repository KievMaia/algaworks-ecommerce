package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;

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
}
