package com.algaworks.ecommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@ManyToOne
	@JoinColumn(name = "categoria_pai_id")
	private Categoria categoriaPai;
	
	@OneToMany(mappedBy = "categoriaPai")
	private List<Categoria > categorias;
}
