package com.elmanalubricentro.ElMana.proveedor.service;

import com.elmanalubricentro.ElMana.producto.dto.ProductoSinProveedorDTO;
import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.proveedor.dto.ProveedorCantidadProductosDTO;
import com.elmanalubricentro.ElMana.proveedor.dto.ProveedorConProductosDTO;
import com.elmanalubricentro.ElMana.proveedor.dto.ProveedorSinProductosDTO;
import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import com.elmanalubricentro.ElMana.proveedor.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    IProveedorRepository proveedorRepository;

    public Optional<Proveedor> getById(Long id) {
        return proveedorRepository.findById(id);
    }

    public List<Proveedor> getProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        List<Proveedor> proveedoresActivos = new ArrayList<>();

        for(Proveedor proveedor : proveedores) {
            
            if(proveedor.isProveedor_activo())
                proveedoresActivos.add(proveedor);
        }

        return proveedoresActivos;
    }

    public List<ProveedorCantidadProductosDTO> getProveedoresWithProductosAmount() {
        List<Proveedor> proveedores = this.getProveedores();
        List<ProveedorCantidadProductosDTO> proveedoresCantidadProductosDTO = new ArrayList<>();
    
        for(Proveedor proveedor : proveedores) {
            
            ProveedorCantidadProductosDTO proveedorConCantidadProductos = this.proveedorToProveedorCantidadProductosDTO(proveedor);
            proveedoresCantidadProductosDTO.add(proveedorConCantidadProductos);
        }
        
        return proveedoresCantidadProductosDTO;
    }


    public ProveedorConProductosDTO getProveedorWithProductos(Long id) {
        Optional<Proveedor> proveedor = this.getById(id);
        ProveedorConProductosDTO proveedorConProductosDTO = null;
        
        if (proveedor.isPresent()) {

            List<Producto> productosProveedor = proveedor.get().getProductos();
            List<ProductoSinProveedorDTO> productosSinProveedor = new ArrayList<>();

            for(Producto producto : productosProveedor) {

                if(producto.isProducto_activo()) {
                    ProductoSinProveedorDTO productoSinProveedorDTO = this.productoToProductoSinProveedor(producto);
                    productosSinProveedor.add(productoSinProveedorDTO);
                }
            }

            proveedorConProductosDTO = proveedorToProveedorConProductosDTO(proveedor.get(), productosSinProveedor);
        }

        return proveedorConProductosDTO;
    }

    public ProductoSinProveedorDTO productoToProductoSinProveedor(Producto producto) {
        ProductoSinProveedorDTO productoSinProveedorDTO = new ProductoSinProveedorDTO(
            producto.getId_producto(),
            producto.getProducto_name(),
            producto.getDescription(),
            producto.getBrand(),
            producto.getCost(),
            producto.getPrice(),
            producto.getStock(),
            producto.getProducto_note());
        
        return productoSinProveedorDTO;
    }

    public ProveedorSinProductosDTO getProveedorSinProductos(Long id) {
        Optional<Proveedor> proveedor = this.getById(id);
        ProveedorSinProductosDTO proveedorSinProductosDTO = null;

        if(proveedor.isPresent()) {
            proveedorSinProductosDTO = this.proveedorToProveedorSinProductosDTO(proveedor.get());    
        }
        
        return proveedorSinProductosDTO;
    }
    
    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void deleteProveedor(Long id) {
        this.getById(id).ifPresent(proveedor -> {
            proveedor.setProveedor_activo(false);
            this.saveProveedor(proveedor);
        });
    }

    public Proveedor editProveedor(Proveedor proveedor) {
        return this.saveProveedor(proveedor);
    }


    //-------------------------------------- ToDTOs -----------------------------------------------------

    public ProveedorCantidadProductosDTO proveedorToProveedorCantidadProductosDTO(Proveedor proveedor) {
        ProveedorCantidadProductosDTO proveedorConCantidadProductos = 
        new ProveedorCantidadProductosDTO(proveedor.getId_proveedor(),
                                          proveedor.getProveedor_name(),
                                          proveedor.getProductos().size());

        return proveedorConCantidadProductos;
    }

    public ProveedorConProductosDTO proveedorToProveedorConProductosDTO(Proveedor proveedor, 
                                                                        List<ProductoSinProveedorDTO> productosSinProveedor) {
        ProveedorConProductosDTO proveedorConProductosDTO = new ProveedorConProductosDTO(
            proveedor.getId_proveedor(),
            proveedor.getProveedor_name(),
            proveedor.getEmail(),
            proveedor.getPhone(),
            proveedor.getWhatsapp(),
            proveedor.getAdress(),
            proveedor.getProveedor_note(),
            productosSinProveedor);
        
        return proveedorConProductosDTO;
    }

    public ProveedorSinProductosDTO proveedorToProveedorSinProductosDTO(Proveedor proveedor) {
        ProveedorSinProductosDTO proveedorSinProductosDTO = new ProveedorSinProductosDTO(
            proveedor.getId_proveedor(),
            proveedor.getProveedor_name(),
            proveedor.getEmail(),
            proveedor.getPhone(),
            proveedor.getWhatsapp(),
            proveedor.getAdress(),
            proveedor.getProveedor_note()
        );  
    
        return proveedorSinProductosDTO;
    }
}
