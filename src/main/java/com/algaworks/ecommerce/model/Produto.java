package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

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
@Table(name = "produto",
       uniqueConstraints = {@UniqueConstraint(name = "unq_nome", columnNames = {"nome"})},
       indexes = {@Index(name = "idxnome", columnList = "nome")})
public class Produto extends EntidadeBaseInteger {

    @Column(name = "data_criacao", updatable = false, nullable = false)
    //updatable false, garante que esse atributo não seja atualizado. O padrão é true, por isso colocamos false.
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    //impede a inserção na coluna data_ultima_atualizacao na criação do registro.
    private LocalDateTime dataUltimaAtualizacao;

    @Column(length = 100, nullable = false)
    private String nome;

    @Lob
    private String descricao;

    private BigDecimal preco;

    @Lob
    private byte[] foto;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
               joinColumns = @JoinColumn(name = "produto_id", nullable = false,
                                         foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),
               inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false,
                                                foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria")))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @CollectionTable(name = "produto_tag",
                     joinColumns = @JoinColumn(name = "produto_id", nullable = false,
                                               foreignKey = @ForeignKey(name = "fk_produto_tag_produto")))
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
                     joinColumns = @JoinColumn(name = "produto_id", nullable = false,
                                               foreignKey = @ForeignKey(name = "fk_produto_atributo_produto")))
    private List<Atributo> atributos;

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
    }
}
