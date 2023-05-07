package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;

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
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @Column(name = "data_criacao", updatable = false, nullable = false)
    //updatable false, garante que esse atributo não seja atualizado. O padrão é true, por isso colocamos false.
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    //impede a inserção na coluna data_ultima_atualizacao na criação do registro.
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido entregaEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

    //	@PrePersist
    //	@PreUpdate
    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream()
                    .map(item -> new BigDecimal(item.getQuantidade()).multiply(item.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
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

    //	@PostLoad
    //	public void aposCarregar() {
    //		System.out.println("Após de remover o Pedido");
    //	}
}
