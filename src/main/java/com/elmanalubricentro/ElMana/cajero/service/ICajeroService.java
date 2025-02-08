package com.elmanalubricentro.ElMana.cajero.service;

import java.util.List;

import com.elmanalubricentro.ElMana.cajero.entity.Cajero;

public interface ICajeroService {

    public List<Cajero> getCajeros();

    public void saveCajero(Cajero cajero);

    public void deleteCajero(Long id);

    public Cajero findCajero(Long id);

    public void editCajero(Cajero cajero);
}
