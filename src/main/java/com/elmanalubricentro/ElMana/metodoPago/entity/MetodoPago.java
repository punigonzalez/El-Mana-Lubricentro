package com.elmanalubricentro.ElMana.metodoPago.entity;

import com.elmanalubricentro.ElMana.venta.entity.Venta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="Metodos de Pago")

public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "metodoPago",cascade = CascadeType.ALL,orphanRemoval = true)
    public List<Venta> ventas;









}
