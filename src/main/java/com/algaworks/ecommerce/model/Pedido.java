package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	private Integer id;
	
	private LocalDateTime dataPedido;
	
	private LocalDateTime dataConclusao;
	
	private Integer notaFiscalId;
	
	private BigDecimal total;

	private StatusPedido status;
	
}
