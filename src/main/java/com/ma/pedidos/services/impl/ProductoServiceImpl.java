package com.ma.pedidos.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ma.pedidos.domain.Producto;
import com.ma.pedidos.repositories.IProductoRepository;
import com.ma.pedidos.services.IProductoService;


@Service
public class ProductoServiceImpl implements IProductoService{
	
	private static final Logger logger = LogManager.getLogger(ProductoServiceImpl.class);
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Transactional
	@Override
	public boolean saveProducto(Producto producto) {	
		boolean resul = false;
		try {		
			if(productoRepository.save(producto) != null) 
				resul =  true;
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resul;
	}	

	@Override
	public Optional<Producto> getProducto(UUID id) {
		return productoRepository.findById(id);
	}

	@Transactional
	@Override
	public boolean deleteProducto(UUID id) {
		boolean resul = false;
		try {
			productoRepository.deleteById(id);
			resul = true;
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resul;
	}

}
