package com.ma.pedidos.services;

import java.util.Optional;
import java.util.UUID;

import com.ma.pedidos.domain.Producto;

public interface IProductoService {
	public boolean createProducto(Producto producto);
	public boolean updateProducto(Producto producto);
	public Optional<Producto> getProducto(UUID id);
	public boolean deleteProducto(UUID id);	
}
