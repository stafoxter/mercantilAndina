package com.ma.pedidos.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PRODUCTOS")
public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7455090957637056182L;

	@Id
	private UUID id;
	
	@Column(name = "NOMBRE")
	@NotNull(message = "El nombre del producto no puede estar nulo")
	private String nombre;
	
	@Column(name = "DESCRIPCION_CORTA")
	private String descripcionCorta;
	
	@Column(name = "DESCRIPCION_LARGA")
	private String descripcionLarga;
	
	@Column(name = "PRECIO_UNITARIO")
	@NotNull(message = "El precio del producto no puede estar nulo")
	private double precioUnitario;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}	
	
	
}
