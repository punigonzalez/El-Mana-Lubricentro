package com.elmanalubricentro.ElMana.producto.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoSinProveedorDTO {
    
    private Long id_producto;
    private String producto_name;
    private String description;
    private String brand;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer stock;
    private String producto_note;
}
