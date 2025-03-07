package com.elmanalubricentro.ElMana.producto.entity;

import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import com.elmanalubricentro.ElMana.venta.entity.VentaProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="productos")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    private String producto_name;
    private String description;
    private String brand;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer stock;
    private String producto_note;
    private boolean producto_activo;

    @ManyToMany
    @JoinTable(
            name = "proveedor_producto",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "proveedor_id")
            )
    private List<Proveedor> proveedores;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VentaProducto> ventaProductos;

}
