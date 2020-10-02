package com.ma.pedidos.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.controller.response.ErrorResponse;
import com.ma.pedidos.domain.Pedido;
import com.ma.pedidos.domain.Producto;
import com.ma.pedidos.dtos.PedidoDTO;
import com.ma.pedidos.services.IPedidoService;
import com.ma.pedidos.services.IProductoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/pedidos")
@RequestMapping("/pedidos")
public class PedidoRestController {
	
	@Autowired
	private IPedidoService pedidoService; 

	@ApiOperation(value = "Crear un pedido")
	@PostMapping(path = "")
	public ResponseEntity<Object> crearPedido(@Valid @RequestBody PedidoDTO pedido) {
		PedidoDTO response = pedidoService.createPedido(pedido);
		if(response != null) {
			return ResponseEntity.status(201).body(response);
		} else {
			return ResponseEntity.status(400).body(null);
		}
	}
	
	@ApiOperation(value = "Listar pedidos por fecha")
	@GetMapping(path = "")
	public ResponseEntity<Object> consultarProducto(@RequestParam("fecha") String fecha) {
		Optional<List<Pedido>> response = pedidoService.listPedidos(fecha);
		if(response.isPresent() ){
			return ResponseEntity.status(200).body(response.get());
		}else {
			return ResponseEntity.status(404).body(new ErrorResponse("No se registraron pedidos en la fecha "+ fecha));
		}
	}
}