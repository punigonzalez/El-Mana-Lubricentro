package com.elmanalubricentro.ElMana.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class ProveedorSinProductosDTO {
    private Long id_proveedor;
    private String proveedor_name;
    private String email;
    private String phone;
    private String whatsapp;
    private String adress;
    private String proveedor_note;
}
