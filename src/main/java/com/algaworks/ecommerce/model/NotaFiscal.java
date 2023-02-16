package com.algaworks.ecommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NotaFiscal {

	@EqualsAndHashCode.Include
	@Id
	private Integer id;
	
	private Integer pedido;
	
	private String xml;
	
	private Date dataEmissao;
}
