package org.bedu.java.backend.postwork;


import org.bedu.java.backend.postwork.persistence.*;
import org.bedu.java.backend.postwork.persistence.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;


@Component
public class InitData implements ApplicationRunner {
    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final EtapaRepository etapaRepository;

    @Autowired
    private final ProductoRepository productoRepository;

    @Autowired
    private final VentaRepository ventaRepository;

    @Autowired
    private final VisitaRepository visitaRepository;

    public InitData(ClienteRepository clienteRepository,
                    EtapaRepository etapaRepository,
                    ProductoRepository productoRepository,
                    VentaRepository ventaRepository,
                    VisitaRepository visitaRepository) {
        this.clienteRepository = clienteRepository;
        this.etapaRepository = etapaRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.visitaRepository = visitaRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Cliente

        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente Super");
        cliente.setCorreoContacto("cliente.super@gmail.com");
        cliente.setDireccion("Mi Villa Hermosa");
        cliente.setNumEmpleados("10");
        clienteRepository.save(cliente);

        // Etapas

        Etapa etapa1 = new Etapa();
        Etapa etapa2 = new Etapa();
        Etapa etapa3 = new Etapa();

        etapa1.setNombre("En espera");
        etapa1.setOrden(1);
        etapa2.setNombre("Venta ganada");
        etapa2.setOrden(2);
        etapa3.setNombre("Venta perdida");
        etapa3.setOrden(3);

        etapaRepository.save(etapa1);
        etapaRepository.save(etapa2);
        etapaRepository.save(etapa3);


        // Producto

        Producto producto = new Producto();
        producto.setNombre("Producto Muy Vendido");
        producto.setCategoria("general");
        producto.setPrecio(30.0F);
        producto.setFechaCreacion(LocalDate.now());
        producto.setNumRegistro("345678");
        productoRepository.save(producto);

        // Venta

        Venta venta = new Venta();
        venta.setFechaCreacion(LocalDateTime.now());
        venta.setCliente(cliente);
        venta.setMonto(1000.0f);
        LinkedList<Producto> productos = new LinkedList<>();
        productos.add(producto);
        venta.setProductos(productos);
        ventaRepository.save(venta);

        // Visita

        Visita visita = new Visita();
        visita.setCliente(cliente);
        visita.setDireccion("Mi Villa Hermosa");
        visita.setVendedor("El Mejor");
        visita.setProposito("Vender todo");
        visita.setFechaProgramada(LocalDateTime.of(2023,6,30,10,0));
        visitaRepository.save(visita);

        System.out.println("------------------------");
        System.out.println("--- Data initialized ---");
        System.out.println("------------------------");

    }


}
