package com.elmanalubricentro.ElMana.proveedor.entity;

import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="proveedores")

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;
    private String proveedor_name;
    private String email;
    private String phone;
    private String whatsapp;
    private String adress;
    private String proveedor_note;
    private boolean proveedor_activo;

    @JsonIgnore
    @ManyToMany(mappedBy = "proveedores", cascade = CascadeType.ALL)
    private List<Producto> productos;

}
