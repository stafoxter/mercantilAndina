package com.ma.pedidos.controller;

import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.controller.response.ErrorResponse;
import com.ma.pedidos.controller.response.ErroresResponse;
import com.ma.pedidos.dtos.PedidoDTO;
import com.ma.pedidos.services.IPedidoService;

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
	public ResponseEntity<Object> crearPedido(@Valid  @RequestBody PedidoDTO pedido) {
		PedidoDTO response = pedidoService.createPedido(pedido);
		if(response != null) {
			return ResponseEntity.status(201).body(response);
		} else {
			return ResponseEntity.status(400).body(null);
		}
	}
	
	@ApiOperation(value = "Listar pedidos por fecha")
	@GetMapping(path = "", params = "fecha")
	public ResponseEntity<Object> consultarProducto(@RequestParam("fecha") String fecha) {
		List<PedidoDTO> response = pedidoService.listPedidos(fecha);
		if(response != null ){
			return ResponseEntity.status(200).body(response);
		}else {
			return ResponseEntity.status(404).body(new ErrorResponse("No se registraron pedidos en la fecha "+ fecha));
		}
	}

	@ApiOperation(value = "Eliminar todos los pedidos")
	@DeleteMapping(path = "/deleteAll/{clave}")
	public ResponseEntity<Object> eliminarPedidos(@PathVariable("clave") String clave) {
		if(clave.equals("unitTest") ){
			pedidoService.deleteAll();
			return ResponseEntity.status(200).body(null);
		}else {
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErroresResponse handleValidationExceptions(
	  MethodArgumentNotValidException ex) {

	    ErroresResponse errores = new ErroresResponse();    
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	    	errores.addError(new ErrorResponse( error.getDefaultMessage()));
	    });
	    errores.getErrores().sort(Comparator.comparing(a -> a.getError()));
	    return errores;
	}

}
