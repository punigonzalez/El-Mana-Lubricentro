package com.elmanalubricentro.ElMana.producto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer stock;
    private String note;
    private Long proveedorId;
}