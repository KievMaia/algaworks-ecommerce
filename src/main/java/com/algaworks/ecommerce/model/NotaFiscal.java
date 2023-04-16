package com.algaworks.ecommerce.model;

import java.util.Date;

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
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

	@EqualsAndHashCode.Include
	@Id
	private Integer id;
	
	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_nota_fiscal_pedido"))
//	@JoinTable(name = "pedido_nota_fiscal", 
//				joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true), 
//				inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true))
	private Pedido pedido;

	@Column(nullable = false)
	@Lob
	private byte[] xml;
	
	@Column(name = "data_emissao", nullable = false)
	private Date dataEmissao;
}
