package org.bedu.java.backend.postwork.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "CLIENTES")
@Entity
@NoArgsConstructor   // Required to make @Builder and @RequiredArgsConstructor work together
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
  
    @Column(name = "correo_contacto", nullable = false)
    private String correoContacto;

   @Column(name = "numero_empleados")
    private String numEmpleados;

    private String direccion;
}
