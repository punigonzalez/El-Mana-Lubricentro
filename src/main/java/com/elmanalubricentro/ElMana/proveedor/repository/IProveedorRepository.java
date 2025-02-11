package com.elmanalubricentro.ElMana.proveedor.repository;

import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor,Long> {

    Optional<Proveedor> findByName(String name);

    @EntityGraph(attributePaths = "productos")  // 🚀 Fuerza la carga de los productos
    Optional<Proveedor> findById(Long id);

}
