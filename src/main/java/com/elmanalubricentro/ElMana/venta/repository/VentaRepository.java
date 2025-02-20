package com.elmanalubricentro.ElMana.venta.repository;
import com.elmanalubricentro.ElMana.venta.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {


}
