package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@EqualsAndHashCode.Include
	@Id
	private Integer id;
	
	@Column(name = "pedido_id")
	private Integer pedidoId;
	
	@Column(name = "produto_id")
	private Integer produtoId;
	
	@Column(name = "preco_produto")
	private BigDecimal precoProduto;
	
	private Long quantidade;
}
