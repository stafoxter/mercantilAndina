package com.ma.pedidos.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ma.pedidos.domain.Estado;
import com.ma.pedidos.domain.Pedido;
import com.ma.pedidos.domain.PedidoDetalle;
import com.ma.pedidos.domain.Producto;
import com.ma.pedidos.dtos.PedidoDTO;
import com.ma.pedidos.dtos.PedidoDetalleDTO;
import com.ma.pedidos.repositories.IPedidoRepository;
import com.ma.pedidos.repositories.IProductoRepository;
import com.ma.pedidos.services.IPedidoService;


@Service
public class PedidoServiceImpl implements IPedidoService{
	
	private static final int CANT_MAX_SIN_DESCUENTO = 3;
	private static final float PORCENTAJE_DESCUENTO = 30;

	private static final Logger logger = LogManager.getLogger(PedidoServiceImpl.class);
	
	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Transactional
	@Override
	public PedidoDTO createPedido(PedidoDTO pedidoDTO) {
		PedidoDTO resul = null;
		try {
			Pedido pedidoPersist = pedidoRepository.save(this.convertDTOtoPedido(pedidoDTO));	
			resul = convertPedidotoDTO(pedidoPersist);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resul;
	}

	@Override
	public List<PedidoDTO> listPedidos(String fecha) {
		Optional<List<Pedido>> pedidosPersist =null;
		List<PedidoDTO> pedidosDTO = null;
		PedidoDTO pedidoDTO = null;
		try {
			pedidosPersist = pedidoRepository.findByFechaAlta(LocalDate.parse(fecha));
			if(pedidosPersist.isPresent()) {
				pedidosDTO = new ArrayList<>();
				for (Pedido pedido : pedidosPersist.get()) {
					pedidoDTO = this.convertPedidotoDTO(pedido);
					pedidoDTO.setEstado(null);
					pedidosDTO.add(pedidoDTO);
				}
			}			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}

		return pedidosDTO;
	}

	private double calcularImporteTotalPedido(Pedido pedido) {
		double total = this.getImporteTotalPedido(pedido);
		if(this.aplicaDescuento(pedido)) {
			return total * (100-PORCENTAJE_DESCUENTO)/100;
		}else {
			return total;
		}
	
	}

	private int getCantidadProductosPedido(Pedido pedido) {
		if(pedido != null && pedido.getPedidoDetalles() != null)
			return pedido.getPedidoDetalles().stream().mapToInt(o -> o.getCantidad()).sum();
		
		return 0;
	}
	
	private double getImporteTotalPedido(Pedido pedido) {
		if(pedido != null && pedido.getPedidoDetalles() != null)
			return pedido.getPedidoDetalles().stream().mapToDouble(det -> det.getPrecioUnitario()).sum();
		
		return 0;

	}
	
	private boolean aplicaDescuento(Pedido pedido) {
		if(this.getCantidadProductosPedido(pedido) > CANT_MAX_SIN_DESCUENTO) {
			return true;
		}else {
			return false;
		}
	}
	
	private Pedido convertDTOtoPedido(PedidoDTO pedidoDTO) {

		Pedido pedido = null;
		if(pedidoDTO == null)
			return null;
		
		pedido = new Pedido();
		pedido.setDireccion(pedidoDTO.getDireccion());
		pedido.setEmail(pedidoDTO.getEmail());
		pedido.setEstado(Estado.PENDIENTE);
		pedido.setFechaAlta(pedidoDTO.getFecha() != null ?
				LocalDate.parse(pedidoDTO.getFecha()) : LocalDate.now());
		pedido.setHorario( pedidoDTO.getHorario() != null ?
				 LocalTime.parse(pedidoDTO.getHorario(), DateTimeFormatter.ofPattern("H:mm")) : 
					 LocalTime.now() );
		pedido.setTelefono(pedidoDTO.getTelefono());
		
		this.agregarDetallesPedido(pedidoDTO, pedido);
		
		pedido.setAplicoDescuento(this.aplicaDescuento(pedido));
		pedido.setMontoTotal(this.calcularImporteTotalPedido(pedido));
		
		return pedido;

	}
	

	private void agregarDetallesPedido(PedidoDTO pedidoDTO, Pedido pedido) {
		if(pedidoDTO.getDetalle() == null) 
			return;
		
		List<PedidoDetalle> detallesPedido = new ArrayList<>();
		PedidoDetalle itemDetalle = null;
		List<PedidoDetalleDTO> detalleDTO;
		List<UUID> ids = new ArrayList<>();

		for (PedidoDetalleDTO det : pedidoDTO.getDetalle()) {
			ids.add(UUID.fromString(det.getProducto()));
		}
		List<Producto> productos = productoRepository.findAllById(ids);
		
		for (Producto prod : productos) {
			itemDetalle = new PedidoDetalle();
			itemDetalle.setProducto(prod);
					
			detalleDTO = pedidoDTO.getDetalle().stream().filter(d -> 
					UUID.fromString(d.getProducto()).compareTo(prod.getId()) == 0 )
					.collect(Collectors.toList());
			
			// Si se selecciono un producto registrado
			if(!detalleDTO.isEmpty()) { 

				// Si el producto esta en más de un detalle
				if(detalleDTO.size() > 1) {			
					itemDetalle.setCantidad(detalleDTO.stream().mapToInt(o -> o.getCantidad()).sum());
	
				// Si el producto esta en un sólo detalle
				}else {
					itemDetalle.setCantidad(detalleDTO.get(0).getCantidad());
				}	
				
				itemDetalle.setPrecioUnitario(prod.getPrecioUnitario() * itemDetalle.getCantidad());
				
				detallesPedido.add(itemDetalle);		
			}
		}
		pedido.setPedidoDetalles(detallesPedido);
		
	}
	
	private PedidoDTO convertPedidotoDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = null;
		if(pedido == null)
			return null;
		
		pedidoDTO = new PedidoDTO();
		pedidoDTO.setDireccion(pedido.getDireccion());
		pedidoDTO.setEmail(pedido.getEmail());
		pedidoDTO.setEstado(pedido.getEstado().toString());
		pedidoDTO.setFecha(pedido.getFechaAlta().format(DateTimeFormatter.ISO_LOCAL_DATE));
		pedidoDTO.setHorario( pedido.getHorario().format(DateTimeFormatter.ofPattern("H:mm")));	
		pedidoDTO.setTelefono(pedido.getTelefono());
		pedidoDTO.setTotal(pedido.getMontoTotal());
		pedidoDTO.setDescuento(pedido.isAplicoDescuento());
		
		this.agregarDetallesPedidoDTO(pedidoDTO, pedido);

		return pedidoDTO;		
	}
	
	private void agregarDetallesPedidoDTO(PedidoDTO pedidoDTO, Pedido pedido) {
		List<PedidoDetalleDTO> detallesPedidoDTO = new ArrayList<>();
		PedidoDetalleDTO itemDetalleDTO = null;
		
		for (PedidoDetalle det : pedido.getPedidoDetalles()) {
			itemDetalleDTO = new PedidoDetalleDTO();
			itemDetalleDTO.setProducto(det.getProducto().getId().toString());
			itemDetalleDTO.setNombre(det.getProducto().getNombre());
			itemDetalleDTO.setCantidad(det.getCantidad());
			itemDetalleDTO.setImporte(det.getPrecioUnitario());
			
			detallesPedidoDTO.add(itemDetalleDTO);
		}
		
		pedidoDTO.setDetalle(detallesPedidoDTO);		
	}

	@Override
	public void deleteAll() {
		pedidoRepository.deleteAll();	
	}
	
}
