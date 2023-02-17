package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@TableGenerator(name = "tabela", 
					table = "hibernate_sequences", 
					pkColumnName = "sequence_name", 
					pkColumnValue = "categoria", 
					valueColumnName = "next_val", 
					initialValue = 0, 
					allocationSize = 50)
	private Integer id;

	private String nome;
	
	@Column(name = "categoria_pai_id")
	private Integer categoriaPaiId;
}
