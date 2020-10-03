package com.ma.pedidos.services;

import java.util.List;

import com.ma.pedidos.dtos.PedidoDTO;

public interface IPedidoService {
	
	public PedidoDTO createPedido(PedidoDTO pedido);
	public List<PedidoDTO> listPedidos(String fecha);
	public void deleteAll();
}
