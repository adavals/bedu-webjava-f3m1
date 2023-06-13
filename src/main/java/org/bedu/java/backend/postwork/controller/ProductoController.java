package org.bedu.java.backend.postwork.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.postwork.model.Producto;
import org.bedu.java.backend.postwork.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long productoId){
        Optional<Producto> producto = productoService.obtieneProducto(productoId);
        if (producto.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "El producto solicitado no existe");
        }
        return ResponseEntity.ok(producto.get());
    }

    @GetMapping
    public ResponseEntity <List<Producto>> getProductos(){
        return ResponseEntity.ok(productoService.obtieneProductos());
    }

    @PostMapping
    public ResponseEntity<Void> creaProducto(@Valid @RequestBody Producto producto){
        Producto productoCreado = productoService.guardaProducto(producto);
        return ResponseEntity.created(URI.create(String.valueOf(productoCreado.getId()))).build();
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<Void> actualizaProducto(@PathVariable Long productoId, @RequestBody @Valid Producto producto ){
        productoService.actualizaProducto(producto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> eliminaProducto(@PathVariable Long productoId){
        productoService.eliminaProducto(productoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}