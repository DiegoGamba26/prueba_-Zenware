package com.ejemplo.productoapi.repository;

import com.ejemplo.productoapi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
