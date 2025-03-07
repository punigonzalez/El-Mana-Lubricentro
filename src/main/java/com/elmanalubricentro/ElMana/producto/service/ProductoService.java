package com.elmanalubricentro.ElMana.producto.service;

import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.producto.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    public List<Producto> getProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<Producto> productosActivos = new ArrayList<>();

        for(Producto producto : productos) {
            if(producto.isProducto_activo())
                productosActivos.add(producto);
        }

        return productosActivos;
    }
    
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        this.getById(id).ifPresent(producto -> {
            producto.setProducto_activo(false);
            this.saveProducto(producto);
        });
    }

    public Optional<Producto> getById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto editProducto(Producto producto) {
        return this.saveProducto(producto);
    }
}