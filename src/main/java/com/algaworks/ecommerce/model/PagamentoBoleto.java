package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends EntidadeBaseInteger{

	@Column(name = "pedido_id")
	private Integer pedidoId;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;
	
	@Column(name = "codigo_barras")
	private String codigoBarras;
}
