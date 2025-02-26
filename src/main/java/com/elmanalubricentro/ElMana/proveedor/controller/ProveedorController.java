package com.elmanalubricentro.ElMana.proveedor.controller;


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
@RequestMapping("/proveedores")

public class ProveedorController {

    @Autowired
   ProveedorService proveedorService;




    //crear proveedor
    @PostMapping
    ResponseEntity<?> createProveedor(@RequestBody Proveedor p) {
        Proveedor newProveedor = proveedorService.create(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).body(newProveedor);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getProveedorByID2(@PathVariable Long id) {
        try {
            Optional<Proveedor> proveedor = proveedorService.getById(id);
            if (proveedor.isPresent()) {
                return ResponseEntity.ok(proveedor);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");

        } catch (DataAccessException e) {
            // Captura errores específicos de acceso a datos (base de datos)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");
        } catch (Exception e) {
            // Captura cualquier otra excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        }
    }





    @GetMapping("/lista")
    public ResponseEntity<?> getAllProveedores() {
        try {
            List<Proveedor> proveedores = proveedorService.getAll();
            return ResponseEntity.ok(proveedores);
        } catch (DataAccessException e) {
            // Captura errores específicos de acceso a datos (base de datos)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");
        } catch (Exception e) {
            // Captura cualquier otra excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        }
    }

    //obtener proveedor por nombre
    @GetMapping("/nombre/{name}")
    ResponseEntity<?> getProveedorByName(@PathVariable String name) {
        Optional<Proveedor> p = proveedorService.getByName(name);
        if (p.isPresent()) {
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
    }

    // edita lo que se quiere de los proveedores sin modificar los otros datos
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable Long id, @RequestBody Proveedor p) {
        Optional<Proveedor> proveedorOpt = proveedorService.getById(id);

        if (proveedorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
        }

        Proveedor updatedProveedor = proveedorService.update(id, p);
        return ResponseEntity.ok(updatedProveedor);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedorById(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.getById(id);
        if (proveedor.isPresent()) {
            proveedorService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
    }



    //otra forma para el delete usando map()
   /* @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedorById2(@PathVariable Long id) {
        return proveedorService.getById(id)
                .map(proveedor -> {
                    proveedorService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado"));
    }*/
















}
