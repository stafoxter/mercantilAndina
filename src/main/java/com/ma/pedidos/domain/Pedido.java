package com.ma.pedidos.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PEDIDOS_CABECERA")
public class Pedido  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8029044539977554525L;

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "HORARIO")
	private LocalTime horario;
	
	@Column(name = "FECHA_ALTA")
	private LocalDate fechaAlta;	
	
	@Column(name = "MONTO_TOTAL")
	private double montoTotal;	
	
	@Column(name = "APLICO_DESCUENTO")
	private boolean aplicoDescuento;
	
    @Column(name = "ESTADO", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
	private Estado estado;	

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
	private List<PedidoDetalle> pedidoDetalles;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public boolean isAplicoDescuento() {
		return aplicoDescuento;
	}

	public void setAplicoDescuento(boolean aplicoDescuento) {
		this.aplicoDescuento = aplicoDescuento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<PedidoDetalle> getPedidoDetalles() {
		return pedidoDetalles;
	}

	public void setPedidoDetalles(List<PedidoDetalle> pedidoDetalles) {
		this.pedidoDetalles = pedidoDetalles;
	}
	
}
