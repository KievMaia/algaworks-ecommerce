package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class PedidoStatus {
	private Integer id;
	private StatusPedido status;

	public PedidoStatus() {
	}

	public PedidoStatus(Integer id, StatusPedido status) {
		super();
		this.id = id;
		this.status = status;
	}
}