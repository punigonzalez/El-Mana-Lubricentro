package com.elmanalubricentro.ElMana.metodoPago.service;
import com.elmanalubricentro.ElMana.metodoPago.entity.MetodoPago;
import com.elmanalubricentro.ElMana.metodoPago.repository.IMetodoPagoRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService {

    private final IMetodoPagoRepository metodoPagoRepository;

    public MetodoPagoService(IMetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public Optional<MetodoPago> getByID(Long id){
        return metodoPagoRepository.findById(id);
    }

    public MetodoPago create(MetodoPago metodo) {
        // Validar que name no sea null
        if (metodo.getName() == null) {
            throw new IllegalArgumentException("El nombre del método de pago no puede ser null");
        }

        // Validar que name no sea un número
        try {
            Double.parseDouble(metodo.getName());
            // Si llegamos aquí, significa que el nombre es un número
            throw new IllegalArgumentException("El nombre del método de pago no puede ser un número");
        } catch (NumberFormatException e) {
            // Es correcto que lance esta excepción, significa que no es un número
        }

        // Validar que el nombre no exista ya en la base de datos
        if (metodoPagoRepository.existsByName(metodo.getName())) {
            throw new IllegalArgumentException("Ya existe un método de pago con el nombre: " + metodo.getName());
        }

        return metodoPagoRepository.save(metodo);
    }

    public List<MetodoPago> getAll(){
        return metodoPagoRepository.findAll();
    }

}
