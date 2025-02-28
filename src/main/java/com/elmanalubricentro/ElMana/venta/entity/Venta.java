package com.elmanalubricentro.ElMana.venta.entity;

import com.elmanalubricentro.ElMana.cliente.entity.Cliente;
import com.elmanalubricentro.ElMana.metodoPago.entity.MetodoPago;
import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name="ventas")
public class Venta {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double total;
    private String detalle;

    @ManyToOne
    @JoinColumn(name = "metodoPago_id")
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VentaProducto> ventaProductos;




}
