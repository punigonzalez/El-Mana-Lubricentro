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

    Optional<MetodoPago> findByName(String name);

    // realiza una consulta sql que por cada producto, devuelve el p.nombre que coincida con el parametro pasado
    @Query("SELECT m FROM MetodoPago m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<MetodoPago> filterByName(@Param("name") String name);

}

