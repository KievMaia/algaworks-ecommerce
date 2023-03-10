package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(ItemPedidoId.class)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@EmbeddedId
	private ItemPedidoId id;
	
	@MapsId("pedidoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@MapsId("produtoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Column(name = "preco_produto")
	private BigDecimal precoProduto;
	
	private Integer quantidade;
}
