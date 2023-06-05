package org.bedu.java.backend.postwork.controller.mappers;

import org.bedu.java.backend.postwork.persistence.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
  Producto productoModelToProductoEntity(org.bedu.java.backend.postwork.model.Producto productoModel);
  org.bedu.java.backend.postwork.model.Producto productoEntityToProductoModel(Producto producto);
}
