package org.bedu.java.backend.postwork.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

public class Venta {
  
  @PositiveOrZero(message = "El identificador de la venta no puede ser negativo.")
    private long ventaId;

    @DecimalMin(value = "1.00", inclusive = true, message = "La venta debe ser de al menos 1.00")
    private float monto;

    @NotEmpty(message = "La venta debe tener por lo menos un producto.")
    private List<Producto> productos;

    @NotNull(message = "La venta debe haberse realizado a algún cliente.")
    private Cliente cliente;

    @PastOrPresent(message = "La venta no puede ocurrir en el futuro.")
    private LocalDateTime fechaCreacion;
}
