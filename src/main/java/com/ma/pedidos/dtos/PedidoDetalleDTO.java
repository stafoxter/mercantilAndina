package com.ma.pedidos.dtos;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PedidoDetalleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8652447804839502469L;
	
	private String producto;
	
	@Min(value = 1, message = "falta ingresar cantidad")
	@NotNull(message = "falta ingresar cantidad")
	private int cantidad;
	private String nombre;
	private double importe;
	
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}

}
