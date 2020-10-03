package com.ma.pedidos.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"fecha","direccion", "email","telefono", 
				"horario","detalle","total", "descuento","estado"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7408754777748026L;
	private String fecha;
	@NotNull(message = "la direccion no puede estar nula")
	private String direccion;
	private String email;
	private String telefono;
	private String horario;
	@Valid
	private List<PedidoDetalleDTO> detalle;
	private double total;
	private boolean descuento;
	private String estado;
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public List<PedidoDetalleDTO> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<PedidoDetalleDTO> detalle) {
		this.detalle = detalle;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public boolean isDescuento() {
		return descuento;
	}
	public void setDescuento(boolean descuento) {
		this.descuento = descuento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
