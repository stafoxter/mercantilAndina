package com.ma.pedidos.services;

import java.util.List;
import java.util.Optional;

import com.ma.pedidos.domain.Pedido;
import com.ma.pedidos.dtos.PedidoDTO;

public interface IPedidoService {
	
	public PedidoDTO createPedido(PedidoDTO pedido);
	public Optional<List<Pedido>> listPedidos(String fecha);
}
