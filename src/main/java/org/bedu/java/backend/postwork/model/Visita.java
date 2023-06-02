package org.bedu.java.backend.postwork.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
@Data
@Builder
@RequiredArgsConstructor
public class Visita {
  
  @PositiveOrZero(message = "El identificador de la visita no puede ser negativo.")
    private long id;
  
    @NotNull(message = "La visita debe haberse realizado a algun cliente")
    private Cliente cliente;
  
    @Future(message = "La fecha de visita no puede ser una fecha pasada.")
    private LocalDateTime fechaProgramada;
  
    @NotEmpty(message = "La direccion no puede estar en blanco")
    @Size(min = 10, message = "La direccion debe tener minimo 10 letras")
    private String direccion;
  
    @NotEmpty(message = "El proposito de la visita no puede ir en blanco")
    @Size(min = 15, message = "El proposito de la visita debe tener minimo 15 caracteres")
    private String proposito;
  
    @NotEmpty(message = "El nombre del vendedor no puede estar en blanco")
    @Size(min = 4, max = 30, message = "El nombre del vendedor debe tener entre 4 y 30 caracteres")
    private String vendedor;
  
}
