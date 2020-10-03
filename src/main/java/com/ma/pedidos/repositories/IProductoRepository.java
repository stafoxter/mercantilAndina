package com.ma.pedidos.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ma.pedidos.domain.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, UUID> {

}
