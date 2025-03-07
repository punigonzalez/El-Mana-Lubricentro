package com.elmanalubricentro.ElMana.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class ProveedorCantidadProductosDTO {
    private Long id_proveedor;
    private String nombre_proveedor;
    private int cantidad_productos;
}
