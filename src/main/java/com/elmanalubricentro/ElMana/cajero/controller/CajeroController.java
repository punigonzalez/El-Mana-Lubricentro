package com.elmanalubricentro.ElMana.cajero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elmanalubricentro.ElMana.cajero.entity.Cajero;
import com.elmanalubricentro.ElMana.cajero.service.ICajeroService;

@RestController
@RequestMapping("/cajero")
public class CajeroController {

    @Autowired
    private ICajeroService cajeroService;

    @PostMapping("/crear")
    public String crearCajero(@RequestBody Cajero cajero) {
        cajeroService.saveCajero(cajero);
        
        return "Cajero creado correctamente";
    }

    @GetMapping("/traer")
    public List<Cajero> traerCajeros() {
        return cajeroService.getCajeros();
    }

    @PutMapping("/editar")
    public String editCajero(@RequestBody Cajero cajero) {
        cajeroService.editCajero(cajero);

        return "Cajero editado correctamente";
    }
       
    @DeleteMapping("/borrar/{id}")
    public String deleteCajero(@PathVariable Long id) {
        cajeroService.deleteCajero(id);

        return "Cajero eliminado correctamente";
    }
 
}
