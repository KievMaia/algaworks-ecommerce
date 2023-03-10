package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.algaworks.ecommerce.listener.GenericoListener;

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
@EntityListeners({GenericoListener.class})
@Table(name = "produto")
public class Produto extends EntidadeBaseInteger{
	
	@Column(name = "data_criacao", updatable = false)//updatable false, garante que esse atributo não seja atualizado. O padrão é true, por isso colocamos false.
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_ultima_atualizacao", insertable = false)//impede a inserção na coluna data_ultima_atualizacao na criação do registro.
	private LocalDateTime dataUltimaAtualizacao;

	private String nome;

	private String descricao;

	private BigDecimal preco;
	
	@Lob
	private byte[] foto;
	
	@ManyToMany
	@JoinTable(name = "produto_categoria", 
				joinColumns = @JoinColumn(name = "produto_id"),
				inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias;
	
	@OneToOne(mappedBy = "produto")
	private Estoque estoque;
	
	@ElementCollection
	@CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"))
	@Column(name = "tag")
	private List<String> tags;
	
	@ElementCollection
	@CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id"))
	private List<Atributo> atributos;
	
	@PrePersist
	public void aoPersistir( ) {
		dataCriacao = LocalDateTime.now();
	}
	
	@PreUpdate
	public void aoAtualizar( ) {
		dataUltimaAtualizacao = LocalDateTime.now();
	}

}
