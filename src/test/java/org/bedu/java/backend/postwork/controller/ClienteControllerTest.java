package org.bedu.java.backend.postwork.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.java.backend.postwork.model.Cliente;
import org.bedu.java.backend.postwork.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @SuppressWarnings("unused")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("unused")
    @MockBean
    private ClienteService clienteService;

    @Test
    void getCliente() throws Exception {
        given(clienteService.obtenCliente(anyLong())).willReturn(Optional.of(Cliente.builder().Id(1L).nombre("Cliente VIP 1").correoContacto("cliente.vip1@vipmail.com").build()));

        mockMvc.perform(get("/cliente/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.correoContacto", is("cliente.vip1@vipmail.com")))
                .andExpect(jsonPath("$.nombre", is("Cliente VIP 1")));
    }

    @Test
    void getClientes() throws Exception {

        List<Cliente> clientes = Arrays.asList(
                Cliente.builder().Id(1L).nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(10)).correoContacto("contacto.vip1@vipmail.com").build(),
                Cliente.builder().Id(2L).nombre("Cliente medio VIP 2").direccion("Villa Dorada").numEmpleados(String.valueOf(15)).correoContacto("casivip@gmail.com").build(),
                Cliente.builder().Id(3L).nombre("Cliente 3").direccion("Mi Colonia Hermosa").numEmpleados(String.valueOf(20)).correoContacto("cliente3@gmail.com").build()
        );

        given(clienteService.obtenClientes()).willReturn(clientes);

        mockMvc.perform(get("/cliente")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].correoContacto", is("contacto.vip1@vipmail.com")))
                .andExpect(jsonPath("$[1].nombre", is("Cliente medio VIP 2")))
                .andExpect(jsonPath("$[2].direccion", is("Mi Colonia Hermosa")));
    }

    @Test
    void creaCliente() throws Exception {
        Cliente clienteParametro = Cliente.builder().nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(1000)).correoContacto("contacto.vip1@vipmail.com").build();
        Cliente clienteRespuesta = Cliente.builder().Id(1L).nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(1000)).correoContacto("contacto.vip1@vipmail.com").build();

        given(clienteService.guardaCliente(clienteParametro)).willReturn(clienteRespuesta);

        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizaCliente() throws Exception {

        Cliente clienteParametro = Cliente.builder().nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(1000)).correoContacto("contacto.vip1@vipmail.com").build();

        mockMvc.perform(put("/cliente/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminaCliente() throws Exception {
        mockMvc.perform(delete("/cliente/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }
}