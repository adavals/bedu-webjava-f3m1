package org.bedu.java.backend.postwork.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Table(name = "PRODUCTOS")
@Entity
@NoArgsConstructor
public class Producto {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
  
    private String nombre;

    private String categoria;
  
    private float precio;
  
    @Column(name = "numero_registro", length = 20)
    private String numRegistro;
  
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}
