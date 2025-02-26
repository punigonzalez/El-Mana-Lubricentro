package com.elmanalubricentro.ElMana.venta.entity;

import com.elmanalubricentro.ElMana.cliente.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDTO {
    private Long clienteId;
    private Cliente clienteData;
    private Long metodoPagoId;
    private String detalle;
    private Set<ProductoVentaDTO> productos;
}