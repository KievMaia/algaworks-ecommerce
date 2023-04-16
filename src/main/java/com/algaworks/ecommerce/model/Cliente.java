package com.algaworks.ecommerce.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"),
				foreignKey = @ForeignKey(name = "fk_cleinte_detalhe_cliente"))
@Entity
@Table(name = "cliente", 
	uniqueConstraints = { @UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"}) },
	indexes = { @Index(name = "idxnome", columnList = "nome") })
public class Cliente extends EntidadeBaseInteger{

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 14, nullable = false)
	private String cpf;
	
	@ElementCollection
	@CollectionTable(name = "cliente_contato", 
			joinColumns = @JoinColumn(name = "cliente_id"), foreignKey = @ForeignKey(name = "fk_cliente_contatos"))
	@MapKeyColumn(name = "tipo")
	@Column(name = "descricao")
	private Map<String, String> contatos;

	@Transient // Especifica a propriedade porém não persite no banco (É ignorado pelo JPA).
	private String primeiroNome;

	@Column(table = "cliente_detalhe", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private SexoCliente sexo;
	
	@Column(name = "data_nascimento", table = "cliente_detalhe")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@PostLoad
	public void configurarPrimeiroNome() {
		if (nome != null && !nome.isBlank()) {
			int index = nome.indexOf(" ");
			if (index > -1) {
				primeiroNome = nome.substring(0, index);
			}
		}
	}
}
