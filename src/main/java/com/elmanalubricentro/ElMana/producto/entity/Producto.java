package com.elmanalubricentro.ElMana.producto.entity;

import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="productos")

public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private BigDecimal costo;
    private BigDecimal precio;
    private Integer stock;
    private String nota;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;


}
