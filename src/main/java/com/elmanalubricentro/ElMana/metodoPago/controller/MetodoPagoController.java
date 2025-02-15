package com.elmanalubricentro.ElMana.metodoPago.controller;


import com.elmanalubricentro.ElMana.metodoPago.entity.MetodoPago;
import com.elmanalubricentro.ElMana.metodoPago.service.MetodoPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodosPago")
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;
    public MetodoPagoController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }







}
