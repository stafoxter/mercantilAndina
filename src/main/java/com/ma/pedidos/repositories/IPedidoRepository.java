package com.ma.pedidos.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ma.pedidos.domain.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, UUID> {
	Optional<List<Pedido>> findByFechaAlta (LocalDate fechaAlta);
}