package org.bedu.java.backend.postwork.controller.mappers;

import org.bedu.java.backend.postwork.persistence.entities.PlantillaCorreo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CorreoMapper {
    PlantillaCorreo correoCorreoModelToCorreoEntity(org.bedu.java.backend.postwork.model.PlantillaCorreo correoModel);
    org.bedu.java.backend.postwork.model.Etapa correoEntityToCorreoModel(PlantillaCorreo correoModel);
}
