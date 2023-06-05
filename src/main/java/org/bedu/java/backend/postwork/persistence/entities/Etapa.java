package org.bedu.java.backend.postwork.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "ETAPAS")
@NoArgsConstructor
public class Etapa {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long etapaId;

    private String nombre;
  
    @Column(unique = true, nullable = false)
    private Integer orden;
}
