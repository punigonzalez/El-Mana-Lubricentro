package com.elmanalubricentro.ElMana.proveedor.controller;


import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import com.elmanalubricentro.ElMana.proveedor.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
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


    // obtener proveedor por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProveedorByID(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.getById(id);
        if (proveedor.isPresent()) {
            return ResponseEntity.ok(proveedor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
    }

    //obtener todos los proveedores
    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorService.getAll();
    }

    //obtener proveedor por nombre
    @GetMapping("/{nombre}")
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
