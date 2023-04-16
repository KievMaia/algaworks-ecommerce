package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.*;

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
	@JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"))
	private Pedido pedido;
	
	@MapsId("produtoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
	private Produto produto;
	
	@Column(name = "preco_produto", nullable = false)
	private BigDecimal precoProduto;

	@Column(columnDefinition = "integer not null")
	private Integer quantidade;
}
