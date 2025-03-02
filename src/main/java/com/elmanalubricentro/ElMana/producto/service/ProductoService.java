package com.elmanalubricentro.ElMana.producto.service;

import com.elmanalubricentro.ElMana.producto.entity.ProductoDTO;
import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.producto.repository.IProductRepository;
import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import com.elmanalubricentro.ElMana.proveedor.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private IProductRepository productoRepository;

    @Autowired
    private IProveedorRepository proveedorRepository;

    // Obtener un producto por ID
    public ProductoDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto con ID " + id + " no encontrado"));

        return convertToDTO(producto);
    }

    // Obtener todos los productos
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Crear un nuevo producto
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Producto producto = convertToEntity(productoDTO);
        Producto savedProducto = productoRepository.save(producto);
        return convertToDTO(savedProducto);
    }

    // Actualizar un producto existente
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto con ID " + id + " no encontrado");
        }

        Producto producto = convertToEntity(productoDTO);
        producto.setId(id); // Asegurar que el ID sea el correcto
        Producto updatedProducto = productoRepository.save(producto);
        return convertToDTO(updatedProducto);
    }

    // Eliminar un producto
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto con ID " + id + " no encontrado");
        }
        productoRepository.deleteById(id);
    }

    // Convertir Producto a ProductoDTO
    private ProductoDTO convertToDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setName(producto.getName());
        dto.setDescription(producto.getDescription());
        dto.setBrand(producto.getBrand());
        dto.setCost(producto.getCost());
        dto.setPrice(producto.getPrice());
        dto.setStock(producto.getStock());
        dto.setNote(producto.getNote());

        // Solo establecer el ID del proveedor, no todo el objeto
        if (producto.getProveedor() != null) {
            dto.setProveedorId(producto.getProveedor().getId());
        }

        return dto;
    }

    // Convertir ProductoDTO a Producto
    private Producto convertToEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setName(dto.getName());
        producto.setDescription(dto.getDescription());
        producto.setBrand(dto.getBrand());
        producto.setCost(dto.getCost());
        producto.setPrice(dto.getPrice());
        producto.setStock(dto.getStock());
        producto.setNote(dto.getNote());

        // Establecer el proveedor si se proporciona un ID
        if (dto.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Proveedor con ID " + dto.getProveedorId() + " no encontrado"));
            producto.setProveedor(proveedor);
        }

        return producto;
    }

    public void restarStock(Integer cantidad){


    }




}