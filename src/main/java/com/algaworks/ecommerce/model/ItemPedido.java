package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class ItemPedido {

	@EqualsAndHashCode.Include
	@Id
	private Integer id;
	
	@ManyToOne
	private Integer pedidoId;
	
	@OneToOne
	private Integer produtoId;
	
	private BigDecimal precoProduto;
	
	private Long quantidade;
}
