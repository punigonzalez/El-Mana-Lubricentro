package com.elmanalubricentro.ElMana.metodoPago.service;

import com.elmanalubricentro.ElMana.metodoPago.entity.MetodoPago;
import com.elmanalubricentro.ElMana.metodoPago.repository.IMetodoPagoRepository;
import com.elmanalubricentro.ElMana.producto.entity.Producto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService {

    private final IMetodoPagoRepository metodoPagoRepository;

    public MetodoPagoService(IMetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }



    public Optional<MetodoPago> getByid(Long id){return metodoPagoRepository.findById(id);}
    public Optional<MetodoPago> getByName(String name){
        return metodoPagoRepository.findByName(name);
    }
    //filter
    public List<MetodoPago> filterByName(String name){
        return metodoPagoRepository.filterByName(name);
    }





}
