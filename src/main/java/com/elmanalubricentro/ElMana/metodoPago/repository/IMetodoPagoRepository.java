package com.elmanalubricentro.ElMana.metodoPago.repository;

import com.elmanalubricentro.ElMana.metodoPago.entity.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMetodoPagoRepository extends JpaRepository<MetodoPago,Long> {
    boolean existsByName(String name);


}

