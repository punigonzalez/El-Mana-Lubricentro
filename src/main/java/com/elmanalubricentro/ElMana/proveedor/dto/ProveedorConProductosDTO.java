package com.elmanalubricentro.ElMana.proveedor.dto;

import java.util.List;

import com.elmanalubricentro.ElMana.producto.dto.ProductoSinProveedorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class ProveedorConProductosDTO {
    private Long id_proveedor;
    private String proveedor_name;
    private String email;
    private String phone;
    private String whatsapp;
    private String adress;
    private String proveedor_note;

    List<ProductoSinProveedorDTO> productos;
}
