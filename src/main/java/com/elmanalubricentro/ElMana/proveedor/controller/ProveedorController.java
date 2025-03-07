package com.elmanalubricentro.ElMana.proveedor.controller;


import com.elmanalubricentro.ElMana.proveedor.dto.ProveedorCantidadProductosDTO;
import com.elmanalubricentro.ElMana.proveedor.dto.ProveedorConProductosDTO;
import com.elmanalubricentro.ElMana.proveedor.dto.ProveedorSinProductosDTO;
import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import com.elmanalubricentro.ElMana.proveedor.service.ProveedorService;
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
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
   ProveedorService proveedorService;

    @PostMapping("/crear")
    ResponseEntity<?> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor newProveedor = proveedorService.saveProveedor(proveedor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proveedor.getId_proveedor())
                .toUri();

        return ResponseEntity.created(location).body(newProveedor);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> getAllProveedores() {
        try {
            List<Proveedor> proveedores = proveedorService.getProveedores();
            return ResponseEntity.ok(proveedores);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        
        }
    }

    @GetMapping("/listaConCantidadDeProductos")
    public ResponseEntity<?> getProveedorWithProductosAmount() {
        try {
            List<ProveedorCantidadProductosDTO> proveedores = proveedorService.getProveedoresWithProductosAmount();
            return ResponseEntity.ok(proveedores);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        
        }
    }

    @GetMapping("/obtenerConProductos/{id}")
    public ResponseEntity<?> getProveedorWithProductos(@PathVariable("id") Long id) {
        try {
            ProveedorConProductosDTO proveedor = proveedorService.getProveedorWithProductos(id);
            return ResponseEntity.ok(proveedor);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        }
    }

    @GetMapping("/obtenerSinProductos/{id}")
    public ResponseEntity<?> getProveedorWithoutProductos(@PathVariable("id") Long id) {
        try {
            ProveedorSinProductosDTO proveedor = proveedorService.getProveedorSinProductos(id);
            return ResponseEntity.ok(proveedor);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> updateProveedor(@RequestBody Proveedor proveedor) {
        proveedorService.editProveedor(proveedor);

        Proveedor updatedProveedor = proveedorService.editProveedor(proveedor);
        return ResponseEntity.ok(updatedProveedor);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteProveedorById(@PathVariable("id") Long id) {
        Optional<Proveedor> proveedor = proveedorService.getById(id);

        if (proveedor.isPresent() && proveedor.get().isProveedor_activo()) {
            proveedorService.deleteProveedor(id);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
    }
}