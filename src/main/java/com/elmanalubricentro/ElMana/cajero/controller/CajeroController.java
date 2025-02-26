package com.elmanalubricentro.ElMana.cajero.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elmanalubricentro.ElMana.cajero.entity.Cajero;
import com.elmanalubricentro.ElMana.cajero.service.CajeroService;

@RestController
@RequestMapping("/cajero")
public class CajeroController {

    @Autowired
    private CajeroService cajeroService;

    @PostMapping("/crear")
    ResponseEntity<?> createCajero(@RequestBody Cajero cajero) {
        Cajero newCajero = cajeroService.saveCajero(cajero);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cajero.getId_cajero())
                .toUri();
        
        return ResponseEntity.created(location).body(newCajero);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> getAllCajeros() {
        try {
            List<Cajero> cajeros = cajeroService.getCajeros();
            return ResponseEntity.ok(cajeros);

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al acceder a la base de datos");

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo conectar al servidor");
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editCajero(@RequestBody Cajero cajero) {
        cajeroService.editCajero(cajero);

        Cajero updatedCajero = cajeroService.editCajero(cajero);
        return ResponseEntity.ok(updatedCajero);
    }
       
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteCajero(@PathVariable Long id) {
        Optional <Cajero> cajero = cajeroService.getById(id);
        
        if(cajero.isPresent() && cajero.get().isActivo()) {
            cajeroService.deleteCajero(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World!";
    }
 
}
