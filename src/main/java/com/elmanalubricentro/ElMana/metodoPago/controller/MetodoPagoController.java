package com.elmanalubricentro.ElMana.metodoPago.controller;
import com.elmanalubricentro.ElMana.metodoPago.entity.MetodoPago;
import com.elmanalubricentro.ElMana.metodoPago.repository.IMetodoPagoRepository;
import com.elmanalubricentro.ElMana.metodoPago.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/metodosPago")

public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;
    public MetodoPagoController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    // Obtener todos los metodos de pago
    @GetMapping
    public ResponseEntity<?> getAllMetodos(){
        List<MetodoPago> lista = metodoPagoService.getAll();
        return ResponseEntity.ok(lista);
    }

    // Obtener metodo de pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMetodoByID(@PathVariable Long id){
        Optional<MetodoPago> metodo = metodoPagoService.getByID(id);
        if(metodo.isPresent()){
            return ResponseEntity.ok(metodo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metodo de pago no encontrado");
    }

    // Crear metodo de pago
    @PostMapping
    public ResponseEntity<?> createMetodoPago(@RequestBody MetodoPago m){
        MetodoPago metodoPago = metodoPagoService.create(m);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(m.getId())
                .toUri();
        return ResponseEntity.created(location).body(metodoPago);
    }


}

