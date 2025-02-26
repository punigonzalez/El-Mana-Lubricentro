package com.elmanalubricentro.ElMana.proveedor.service;


import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import com.elmanalubricentro.ElMana.proveedor.repository.IProveedorRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    IProveedorRepository iproveedorRepository;



    public Optional<Proveedor> getById(Long id){
        return iproveedorRepository.findById(id);
    }

    public List<Proveedor> getAll(){
        return iproveedorRepository.findAll();
    }

    public Optional<Proveedor> getByName(String name){
        return iproveedorRepository.findByName(name);
    }

    public Proveedor create(Proveedor p){

        // Validar que name no sea null
        if (p.getName() == null) {
            throw new IllegalArgumentException("El nombre del proveedor no puede ser null");
        }

        // Validar que name no sea un número
        try {
            Double.parseDouble(p.getName());
            // Si llegamos aquí, significa que el nombre es un número
            throw new IllegalArgumentException("El nombre del proveedor no puede ser un número");
        } catch (NumberFormatException e) {
            // Es correcto que lance esta excepción, significa que no es un número
        }


        if (iproveedorRepository.findByName(p.getName()).isPresent()) {
            throw new IllegalArgumentException("El proveedor "+ p.getName() +" ya está registrado.");
        }
        return iproveedorRepository.save(p);
    }

    public void deleteById(Long id){
        iproveedorRepository.deleteById(id);
    }

    public Proveedor update(Long id, Proveedor p) {
        // Busca el usuario existente en la base de datos
        Proveedor newProveedor = iproveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        // Copia solo las propiedades NO nulas de p a proveedor, excluyendo el campo id
        BeanUtils.copyProperties(p, newProveedor, "id");

        // Guarda el usuario actualizado en la base de datos
        return iproveedorRepository.save(newProveedor);
    }











}
