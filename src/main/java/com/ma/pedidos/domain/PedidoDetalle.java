package com.ma.pedidos.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name="PEDIDOS_DETALLE")
public class PedidoDetalle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7696512467081810428L;

	@Id
	@GeneratedValue
	private UUID id;

    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY) 
	private Producto producto;
	
	@Column(name = "CANTIDAD")
	@Min(value = 1, message = "falta ingresar cantidad")
	private int cantidad;
	
	@Column(name = "PRECIO_UNITARIO")
	private double precioUnitario;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(@Min(value = 1, message = "falta ingresar cantidad") int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

}
