package org.bedu.java.backend.postwork.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.*;
import org.bedu.java.backend.postwork.model.Producto;
import org.bedu.java.backend.postwork.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {
    @SuppressWarnings("unused")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    void getProducto() throws Exception{
        // Prueba para producto id = 1
        given(productoService.obtieneProducto(anyLong()))
                .willReturn(Optional.of(Producto.builder()
                                .id(1L)
                                .nombre("Mi Producto Hermoso")
                                .precio(500.0f)
                                .categoria("unico")
                                .numRegistro("123-456-7777")
                                .fechaCreacion(LocalDate.of(2023,1,1 ))
                                .build())
                    );

        mockMvc.perform(get("/producto/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Mi Producto Hermoso")))
                .andExpect(jsonPath("$.precio", is(500.0)))
                .andExpect(jsonPath("$.categoria", is("unico")))
                .andExpect(jsonPath("$.numRegistro", is("123-456-7777")))
                .andExpect(jsonPath("$.fechaCreacion", is(LocalDate.of(2023,1,1 ).toString())))
        ;
    }

    @Test
    void getProductos() throws Exception {

        List<Producto> productos = Arrays.asList(
                Producto.builder().id(1L).nombre("Producto Hermoso 1").precio(500.0f).categoria("unico").numRegistro("123-456-7777").fechaCreacion(LocalDate.of(2023,1,1 )).build(),
                Producto.builder().id(2L).nombre("Producto Hermoso 2").precio(1500.0f).categoria("unico").numRegistro("123-456-8888").fechaCreacion(LocalDate.of(2023,1,2 )).build(),
                Producto.builder().id(3L).nombre("Producto Hermoso 3").precio(2500.0f).categoria("unico").numRegistro("123-456-9999").fechaCreacion(LocalDate.of(2023,1,3 )).build()
        );

        given(productoService.obtieneProductos()).willReturn(productos);

        mockMvc.perform(get("/producto")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].nombre", is("Producto Hermoso 1")))
                .andExpect(jsonPath("$[1].precio", is(1500.0)))
                .andExpect(jsonPath("$[2].categoria", is("unico")))
                .andExpect(jsonPath("$[0].numRegistro", is("123-456-7777")))
                .andExpect(jsonPath("$[1].fechaCreacion", is(LocalDate.of(2023,1,2 ).toString())));
    }

    @Test
    void creaProducto() throws Exception {
        Producto productoParametro = Producto.builder().id(1L).nombre("Producto Hermoso 1").precio(500.0f).categoria("unico").numRegistro("123-456-7890").fechaCreacion(LocalDate.of(2023,1,1 )).build();
        Producto productoRespuesta = Producto.builder().id(1L).nombre("Producto Hermoso 1").precio(500.0f).categoria("unico").numRegistro("123-456-7890").fechaCreacion(LocalDate.of(2023,1,1 )).build();

        given(productoService.guardaProducto(productoParametro)).willReturn(productoRespuesta);

        // to add support DateTimes types to objectMapper created outside springframework
        ObjectMapper objectMapper = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addModule(new JavaTimeModule()).build();

        mockMvc.perform(post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoParametro)))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizaProducto() throws Exception {

        Producto productoParametro = Producto.builder().id(1L).nombre("Producto Hermoso 1").precio(500.0f).categoria("unico").numRegistro("123-456-7890").fechaCreacion(LocalDate.of(2023,1,1 )).build();

        ObjectMapper objectMapper = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addModule(new JavaTimeModule()).build();

        mockMvc.perform(put("/producto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoParametro)))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminaProducto() throws Exception {

        mockMvc.perform(delete("/producto/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }
}