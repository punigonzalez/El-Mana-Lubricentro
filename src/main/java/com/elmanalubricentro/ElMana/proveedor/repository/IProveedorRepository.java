package com.elmanalubricentro.ElMana.proveedor.repository;

import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor,Long> {

}
