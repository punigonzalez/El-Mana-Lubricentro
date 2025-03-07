package com.elmanalubricentro.ElMana.producto.repository;

import com.elmanalubricentro.ElMana.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {

}

