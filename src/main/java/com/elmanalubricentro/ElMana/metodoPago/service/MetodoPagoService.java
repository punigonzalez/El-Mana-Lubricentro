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

    public MetodoPago create(MetodoPago metodo){
        return metodoPagoRepository.save(metodo);
    }

    public List<MetodoPago> getAll(){
        return metodoPagoRepository.findAll();
    }

}
