package com.elmanalubricentro.ElMana.producto.controller;

import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        Producto createdProducto = productoService.saveProducto(producto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(producto.getId_producto())
                .toUri();

        return ResponseEntity.created(location).body(createdProducto);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> getAllProductos() {
        try {
            List<Producto> productos = productoService.getProductos();
            return ResponseEntity.ok(productos);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto) {
        productoService.editProducto(producto);

        Producto updatedProducto = productoService.editProducto(producto);
        return ResponseEntity.ok(updatedProducto);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> dejeteProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getById(id);

        if (producto.isPresent() && producto.get().isProducto_activo()) {
            productoService.deleteProducto(id);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
    }
}