package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
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
@Entity
@Table(name = "pedido")
public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_ultima_atualizacao")
	private LocalDateTime dataUltimaAtualizacao;
	
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;
	
	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscal;
	
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@Embedded
	private EnderecoEntregaPedido entregaEntrega;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<ItemPedido> itens;
	
	@OneToOne(mappedBy = "pedido")
	private PagamentoCartao pagamento;
	
//	@PrePersist
//	@PreUpdate
	public void calcularTotal() {
		if (itens != null) {
			total = itens.stream()
							.map(ItemPedido::getPrecoProduto)
							.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
	}
	
	@PrePersist
	public void aoPersistir( ) {
		dataCriacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PreUpdate
	public void aoAtualizar( ) {
		dataUltimaAtualizacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PreRemove
	public void aoRemover() {
		System.out.println("Antes de remover o Pedido");
	}
	
	@PostRemove
	public void aposRemover() {
		System.out.println("Após de remover o Pedido");
	}
	
	@PostLoad
	public void aposCarregar() {
		System.out.println("Após de remover o Pedido");
	}
}
