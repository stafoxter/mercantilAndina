package com.ma.pedidos.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","nombre", "descripcionCorta",
		"descripcionLarga", "precioUnitario"})
public class ProductoDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9046888354042620226L;
	
	private String id;
	private String nombre;
	private String descripcionCorta;
	private String descripcionLarga;
	private double precioUnitario;

	// Getter Methods
	public ProductoDTO () {}
	
	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	// Setter Methods

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

}
