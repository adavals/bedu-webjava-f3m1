package org.bedu.java.backend.postwork.controller.mappers;

import org.bedu.java.backend.postwork.persistence.entities.Etapa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtapaMapper {
  Etapa etapaModelToEtapaEntity(org.bedu.java.backend.postwork.model.Etapa etapaModel);
  org.bedu.java.backend.postwork.model.Etapa etapaEntityToEtapaModel(Etapa etapa);
}
