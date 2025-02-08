package com.elmanalubricentro.ElMana.cajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elmanalubricentro.ElMana.cajero.entity.Cajero;

@Repository
public interface ICajeroRepository extends JpaRepository<Cajero, Long>{
}
