package com.elmanalubricentro.ElMana.producto.controller;


import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.producto.service.ProductoService;
import com.elmanalubricentro.ElMana.proveedor.entity.Proveedor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.HTML;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @PostMapping
    ResponseEntity<?> createProducto(@RequestBody Producto p){
        Producto newProducto = productoService.create(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).body(newProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable Long id){
        Optional<Producto> producto = productoService.getById(id);
        if(producto.isPresent()){
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @GetMapping("/lista")
    public ResponseEntity<?> getAllProductos(){
        List<Producto> productos = productoService.getAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/filtrarNombre")
    public ResponseEntity<?> filterProductoByName(@RequestParam("name") String name){
        List<Producto> productos = productoService.filterByName(name);
        if (productos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos");
        }
        return ResponseEntity.ok(productos);
    }



    // edita lo que se quiere de los Productos sin modificar los otros datos
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Producto p) {
        Optional<Producto> ProductoOpt = productoService.getById(id);

        if (ProductoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }

        Producto updatedProducto = productoService.update(id, p);
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductoByID(@PathVariable Long id){
        Optional<Producto> producto = productoService.getById(id);
        if(producto.isPresent()){
            productoService.deleteById(id);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }




}
