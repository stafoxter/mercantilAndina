package com.ma.pedidos.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.controller.response.ErrorResponse;
import com.ma.pedidos.domain.Producto;
import com.ma.pedidos.services.IProductoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/productos")
@RequestMapping("/productos")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService; 
	
	@ApiOperation(value = "Crear un producto")
	@PostMapping(path = "")
	public ResponseEntity<Object> crearProducto(@Valid @RequestBody Producto producto) {
		if(productoService.saveProducto(producto)) {
			return ResponseEntity.status(201).body(null);
		} else {
			return ResponseEntity.status(404).body(null);
		}
	}	

	@ApiOperation(value = "Modificar un producto")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> modificarProducto(@PathVariable("id") UUID id, @RequestBody Producto producto) {
		producto.setId(id);
		if(productoService.saveProducto(producto)) {
			return ResponseEntity.status(204).body(null);
		}else {
			return ResponseEntity.status(404).body("Error al modificar el Producto");
		}
	}	
	
	@ApiOperation(value = "Consultar un producto")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> consultarProducto(@PathVariable("id") UUID id) {
		Optional<Producto> response = productoService.getProducto(id);
		if(response.isPresent() ){
			return ResponseEntity.status(200).body(response.get());
		}else {
			return ResponseEntity.status(404).body(new ErrorResponse("Producto no encontrado"));
		}
	}
	
	@ApiOperation(value = "Borrar un producto")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> BorrarProducto(@PathVariable("id") UUID id) {
		boolean response = productoService.deleteProducto(id);
		if(response ){
			return ResponseEntity.status(204).body(null);
		}else {
			return ResponseEntity.status(404).body(new ErrorResponse("Error al eliminar el Producto"));
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        errors.put("error", error.getDefaultMessage());
	    });
	    return errors;
	}
	

}
