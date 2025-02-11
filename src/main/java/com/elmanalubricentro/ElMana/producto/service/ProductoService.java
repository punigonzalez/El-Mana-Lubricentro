package com.elmanalubricentro.ElMana.producto.service;

import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.producto.repository.IProductRepository;
import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final IProductRepository productRepository;

    public ProductoService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Optional<Producto> getById(Long id){
        return productRepository.findById(id);
    }

    public List<Producto> getAll(){
        return productRepository.findAll();
    }

    public Optional<Producto> getByName(String name){
        return productRepository.findByName(name);
    }

    public Producto create(Producto p){
        if (productRepository.findByName(p.getName()).isPresent()) {
            throw new IllegalArgumentException("El producto "+ p.getName() +" ya está registrado.");
        }
        return productRepository.save(p);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Producto update(Long id, Producto p) {
        // Busca el producto existente en la base de datos
        Producto newProducto = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Copia solo las propiedades NO nulas de p a producto, excluyendo el campo id
        BeanUtils.copyProperties(p, newProducto, "id");

        // Guarda el usuario actualizado en la base de datos
        return productRepository.save(newProducto);
    }


    //filter
    public List<Producto> filterByName(String name){
        return productRepository.filterByName(name);
    }




}
