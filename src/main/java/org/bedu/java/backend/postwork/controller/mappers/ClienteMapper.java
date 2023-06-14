package org.bedu.java.backend.postwork.controller.mappers;

import org.bedu.java.backend.postwork.persistence.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente clienteModelToClienteEntity(org.bedu.java.backend.postwork.model.Cliente clienteModel);

    org.bedu.java.backend.postwork.model.Cliente clienteEntityToClienteModel(Cliente cliente);
}
