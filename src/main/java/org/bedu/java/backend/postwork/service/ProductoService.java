package org.bedu.java.backend.postwork.service;

import lombok.RequiredArgsConstructor;
import org.bedu.java.backend.postwork.controller.mappers.ProductoMapper;
import org.bedu.java.backend.postwork.model.Producto;
import org.bedu.java.backend.postwork.persistence.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public Producto guardaProducto(Producto producto){
        return mapper.productoEntityToProductoModel(
                repository.save(mapper.productoModelToProductoEntity(producto)));
    }

    public List<Producto> obtieneProductos(){
        return repository.findAll().stream()
                .map(producto -> mapper.productoEntityToProductoModel(producto))
                .collect(Collectors.toList());

    }

    public Optional<Producto> obtieneProducto(long idProducto){
        return repository.findById(idProducto)
                .map(producto -> Optional.of(mapper.productoEntityToProductoModel(producto)))
                .orElse(Optional.empty());
    }

    public void eliminaProducto(long idProducto){
        repository.deleteById(idProducto);
    }

    public Producto actualizaProducto(Producto producto){
        return mapper.productoEntityToProductoModel(
                repository.save(mapper.productoModelToProductoEntity(producto))
        );
    }

    public long cuentaProductos(){
        return repository.count();
    }


}
