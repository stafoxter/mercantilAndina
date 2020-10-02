package com.ma.pedidos.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PEDIDOS_DETALLE")
public class PedidoDetalle implements Serializable {
	
	@Id
	@GeneratedValue
	private UUID id;
	
  /*  @ManyToOne(fetch = FetchType.LAZY, optional = false)  
	@JoinColumn(name = "PEDIDO_CABECERA_ID", nullable = false)
	private Pedido pedido;
*/
	
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Producto producto;
	
	@Column(name = "CANTIDAD")
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

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}	
	
	

}
