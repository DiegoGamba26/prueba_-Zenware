package com.ejemplo.productoapi.controller;

import com.ejemplo.productoapi.model.Producto;
import com.ejemplo.productoapi.repository.ProductoRepository;
import com.ejemplo.productoapi.exception.NoProductosException;
import com.ejemplo.productoapi.exception.ProductoNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Producto> getAllProductos() {
        List<Producto> productos = repository.findAll();
        if (productos.isEmpty()) {
            throw new NoProductosException();
        }
        return productos;
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return repository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existente = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        
        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setDescripcion(producto.getDescripcion());
        
        return repository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
