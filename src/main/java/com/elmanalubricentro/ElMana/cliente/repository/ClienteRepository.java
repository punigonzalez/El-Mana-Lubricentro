package com.elmanalubricentro.ElMana.cliente.repository;

import com.elmanalubricentro.ElMana.cliente.entity.Cliente;
import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByName(String name);

    // realiza una consulta sql que por cada cliente, devuelve el c.nombre que coincida con el parametro pasado
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Cliente> filterByName(@Param("name") String name);


}
