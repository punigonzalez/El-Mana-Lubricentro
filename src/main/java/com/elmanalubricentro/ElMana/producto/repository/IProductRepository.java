package com.elmanalubricentro.ElMana.producto.repository;

import com.elmanalubricentro.ElMana.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Producto,Long> {

    Optional<Producto> findByName(String name);

    // realiza una consulta sql que por cada producto, devuelve el p.nombre que coincida con el parametro pasado
    @Query("SELECT p FROM Producto p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Producto> filterByName(@Param("name") String name);
}

