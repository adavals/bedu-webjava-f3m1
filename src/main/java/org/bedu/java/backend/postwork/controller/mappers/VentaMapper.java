package org.bedu.java.backend.postwork.controller.mappers;

import org.bedu.java.backend.postwork.persistence.entities.Venta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaMapper {
  Venta ventaModelToVentaEntity(org.bedu.java.backend.postwork.model.Venta ventaModel);
  org.bedu.java.backend.postwork.model.Venta ventaEntityToVentaModel(Venta venta);
}
