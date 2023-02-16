package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categoria")
public class Categoria {

	@EqualsAndHashCode.Include
	@Id
	private Integer id;

	private String nome;
	
	@Column(name = "categoria_pai_id")
	private Integer categoriaPaiId;
}
