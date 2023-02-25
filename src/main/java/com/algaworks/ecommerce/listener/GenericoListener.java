package com.algaworks.ecommerce.listener;

import javax.persistence.PostLoad;

public class GenericoListener {

	@PostLoad
	public void logCarregamento(Object obj) {
		System.out.println(String.format("Entidade %s foi carregada", obj.getClass().getSimpleName()));
	}
}
