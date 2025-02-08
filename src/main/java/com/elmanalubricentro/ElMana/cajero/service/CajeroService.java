package com.elmanalubricentro.ElMana.cajero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elmanalubricentro.ElMana.cajero.entity.Cajero;
import com.elmanalubricentro.ElMana.cajero.repository.ICajeroRepository;

@Service
public class CajeroService implements ICajeroService{

    @Autowired
    private ICajeroRepository cajeroRepository;

    @Override
    public List<Cajero> getCajeros() {
        return cajeroRepository.findAll();
    }

    @Override
    public void saveCajero(Cajero cajero) {
        cajeroRepository.save(cajero);
    }

    @Override
    public void deleteCajero(Long id) {
        
        Cajero cajeroAEliminar = this.findCajero(id);

        cajeroAEliminar.setActivo(false);

        this.saveCajero(cajeroAEliminar);
    }

    @Override
    public Cajero findCajero(Long id) {
        return cajeroRepository.findById(id).orElse(null);
    }

    @Override
    public void editCajero(Cajero cajero) {
        this.saveCajero(cajero);
    }
}
