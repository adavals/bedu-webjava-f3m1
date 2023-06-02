package org.bedu.java.backend.postwork.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.time.LocalDate;
@Data
@Builder
@RequiredArgsConstructor
public class Producto {
  
  @PositiveOrZero(message = "El identificador del producto no puede ser negativo.")
    private long id;
  
    @NotEmpty(message = "El nombre del producto no puede estar en blanco.")
    @Size(min = 4, max = 30, message = "El nombre del producto debe tener entre 4 y 30 caracteres")
    private String nombre;
    private String categoria;
  
    @DecimalMin(value = "1.00", inclusive = true, message = "El precio debe ser al menos 1.00")
    private float precio;
  
    @NotEmpty(message = "El numero de registro del producto no puede estar en blanco.")
    @Pattern(regexp = "^(\\d{3}[-]?){2}\\d{4}$")
    private String numRegistro;
  
    @PastOrPresent(message = "La fecha de creacion del producto no puede ocurrir en el futuro.")
    private LocalDate fechaCreacion;
}
