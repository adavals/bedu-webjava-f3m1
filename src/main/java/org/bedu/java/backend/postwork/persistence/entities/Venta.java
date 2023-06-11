package org.bedu.java.backend.postwork.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Table(name = "VENTAS")
@Entity
@NoArgsConstructor
public class Venta {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ventaId;

    private float monto;

    @OneToMany
    private List<Producto> productos;

    @ManyToOne
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
}
