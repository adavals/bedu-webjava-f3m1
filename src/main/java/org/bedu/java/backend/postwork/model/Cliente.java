package org.bedu.java.backend.postwork.model;

public class Cliente {
  @PositiveOrZero(message = "El identificador no puede ser un numero negativo")
    private long id;
  
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
