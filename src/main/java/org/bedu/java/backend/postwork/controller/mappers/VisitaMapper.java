package org.bedu.java.backend.postwork.controller.mappers;

import org.bedu.java.backend.postwork.persistence.entities.Visita;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitaMapper {
  Visita visitaModelToVisitaEntity(org.bedu.java.backend.postwork.model.Visita visitaModel);
  org.bedu.java.backend.postwork.model.Visita visitaEntityToVisitaModel(Visita visita);
}
