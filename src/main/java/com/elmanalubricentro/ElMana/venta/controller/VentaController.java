package com.elmanalubricentro.ElMana.venta.controller;

import com.elmanalubricentro.ElMana.venta.entity.VentaRequestDTO;
import com.elmanalubricentro.ElMana.venta.entity.Venta;
import com.elmanalubricentro.ElMana.venta.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody VentaRequestDTO ventaDTO) {
        Venta nuevaVenta = ventaService.crearVenta(ventaDTO);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }
}