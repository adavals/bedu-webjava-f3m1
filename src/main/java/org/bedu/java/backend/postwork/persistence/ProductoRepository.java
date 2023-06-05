package org.bedu.java.backend.postwork.persistence;

import org.bedu.java.backend.postwork.persistence.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
