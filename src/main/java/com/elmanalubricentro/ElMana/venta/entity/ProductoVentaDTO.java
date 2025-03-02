package com.elmanalubricentro.ElMana.venta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoVentaDTO {
    private Long productoId;
    private Integer cantidad;
}


