package com.elmanalubricentro.ElMana.cliente.controller;

import com.elmanalubricentro.ElMana.cliente.entity.Cliente;
import com.elmanalubricentro.ElMana.cliente.service.ClienteService;
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
@RequestMapping("/api/clientes")

public class ClienteController {
    @Autowired
    ClienteService clienteService;


    //crear proveedor
    @PostMapping
    ResponseEntity<?> createCliente(@RequestBody Cliente c) {
        Cliente newCLiente = clienteService.create(c);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.getId())
                .toUri();
        return ResponseEntity.created(location).body(newCLiente);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteByID(@PathVariable Long id) {
        try {
            Optional<Cliente> cliente = clienteService.getById(id);
            if (cliente.isPresent()) {
                return ResponseEntity.ok(cliente);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");

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





    @GetMapping
    public ResponseEntity<?> getAllClientes() {
        try {
            List<Cliente> clientes = clienteService.getAll();
            return ResponseEntity.ok(clientes);
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
    ResponseEntity<?> getClienteByName(@PathVariable String name) {
        Optional<Cliente> cliente = clienteService.getByName(name);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    // edita lo que se quiere de los proveedores sin modificar los otros datos
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente c) {
        Optional<Cliente> clienteOpt = clienteService.getById(id);

        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }

        Cliente updatedCliente = clienteService.update(id, c);
        return ResponseEntity.ok(updatedCliente);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getById(id);
        if (cliente.isPresent()) {
            clienteService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }










}
