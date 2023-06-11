package org.bedu.java.backend.postwork.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor   // Required to make @Builder and @RequiredArgsConstructor work together
public class Cliente {
  @PositiveOrZero(message = "El identificador no puede ser un numero negativo")
    private long Id;
  
    @NotEmpty(message = "El campo no puede estar vacio.")
    @Size(min = 5, max = 30, message = "El nombre debe ser entre 5 y 30 letras.")
    private String nombre;
  
    @Email
    private String correoContacto;

    @Min(value = 10, message = "Los clientes con menos de 10 empleados no son validos")
    @Max(value = 10000, message = "Los clientes con mas de 10000 empleados no son validos")
    private String numEmpleados;
  
    @NotBlank(message = "Se debe ingresar una direccion.")
    private String direccion;
}
