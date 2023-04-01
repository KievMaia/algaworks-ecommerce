package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnderecoEntregaPedido {

	@Column(length = 9)
	private String cep;

	@Column(length = 100)
	private String logradouro;

	@Column(length = 10)
	private String numero;

	@Column(length = 50)
	private String complemento;

	@Column(length = 50)
	private String bairro;

	@Column(length = 50)
	private String cidade;

	@Column(length = 2)
	private String estado;
}
