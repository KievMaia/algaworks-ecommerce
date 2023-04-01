package com.algaworks.ecommerce.model;

import javax.persistence.*;

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
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger{

	@OneToOne(optional = false)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@Column(columnDefinition = "integer not null")
	private Integer quantidade;
}
