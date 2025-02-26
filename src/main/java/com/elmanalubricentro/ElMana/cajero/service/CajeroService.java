package com.elmanalubricentro.ElMana.cajero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elmanalubricentro.ElMana.cajero.entity.Cajero;
import com.elmanalubricentro.ElMana.cajero.repository.ICajeroRepository;

@Service
public class CajeroService {

    @Autowired
    private ICajeroRepository cajeroRepository;

    public List<Cajero> getCajeros() {
        List<Cajero> cajeros = cajeroRepository.findAll();
        List<Cajero> cajerosActivos = new ArrayList<>();

        for(Cajero cajero : cajeros) {
            if(cajero.isActivo())
                cajerosActivos.add(cajero);
        }
        
        return cajerosActivos;
    }

    public Cajero saveCajero(Cajero cajero) {
        return cajeroRepository.save(cajero);
    }

    public void deleteCajero(Long id) {
        this.getById(id).ifPresent(cajero -> {
            cajero.setActivo(false);
            this.saveCajero(cajero);
        });
    }

    public Optional<Cajero> getById(Long id) {
        return cajeroRepository.findById(id);
    }

    public Cajero editCajero(Cajero cajero) {
        return this.saveCajero(cajero);
    }
}
