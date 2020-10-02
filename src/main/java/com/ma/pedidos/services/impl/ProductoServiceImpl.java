package com.ma.pedidos.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ma.pedidos.domain.Producto;
import com.ma.pedidos.repositories.IProductoRepository;
import com.ma.pedidos.services.IProductoService;


@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	public boolean createProducto(Producto producto) {		
		Producto productoPersist = productoRepository.save(producto);
		if(productoPersist != null) {
			return true;
		}else {
			return false;
		}
	}	

	@Override
	public Optional<Producto> getProducto(UUID id) {
		return productoRepository.findById(id);
	}

	@Override
	public boolean updateProducto(Producto producto) {
		Producto productoPersist = productoRepository.save(producto);
		if(productoPersist != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteProducto(UUID id) {
		try {
			productoRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
