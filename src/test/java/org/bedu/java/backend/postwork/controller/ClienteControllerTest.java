package org.bedu.java.backend.postwork.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.java.backend.postwork.model.Cliente;
import org.bedu.java.backend.postwork.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
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
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "bedu.org/rest", uriPort = 80)
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
        given(clienteService.obtenCliente(anyLong())).willReturn(Optional.of(Cliente.builder().id(1L).nombre("Cliente VIP 1").correoContacto("cliente.vip1@vipmail.com").build()));

        mockMvc.perform(get("/cliente/{clienteId}",1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.correoContacto", is("cliente.vip1@vipmail.com")))
                .andExpect(jsonPath("$.nombre", is("Cliente VIP 1")))
                .andDo(document("cliente/get-cliente",
                        pathParameters(
                                parameterWithName("clienteId").description("Identificador del cliente")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador del cliente"),
                                fieldWithPath("nombre").description("nombre del cliente"),
                                fieldWithPath("correoContacto").description("correo de contacto del cliente"),
                                fieldWithPath("numEmpleados").description("número de trabajadores del cliente"),
                                fieldWithPath("direccion").description("domicilio del cliente")
                        )));
    }

    @Test
    void getClientes() throws Exception {

        List<Cliente> clientes = Arrays.asList(
                Cliente.builder().id(1L).nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(10)).correoContacto("contacto.vip1@vipmail.com").build(),
                Cliente.builder().id(2L).nombre("Cliente medio VIP 2").direccion("Villa Dorada").numEmpleados(String.valueOf(15)).correoContacto("casivip@gmail.com").build(),
                Cliente.builder().id(3L).nombre("Cliente 3").direccion("Mi Colonia Hermosa").numEmpleados(String.valueOf(20)).correoContacto("cliente3@gmail.com").build()
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
                .andExpect(jsonPath("$[2].direccion", is("Mi Colonia Hermosa")))
                .andDo(document("cliente/get-clientes",
                        responseFields(
                                fieldWithPath("[].id").description("identificador del cliente"),
                                fieldWithPath("[].nombre").description("nombre del cliente"),
                                fieldWithPath("[].correoContacto").description("correo de contacto del cliente"),
                                fieldWithPath("[].numEmpleados").description("número de trabajadores del cliente"),
                                fieldWithPath("[].direccion").description("domicilio del cliente")
                        )));
    }

    @Test
    void creaCliente() throws Exception {
        Cliente clienteParametro = Cliente.builder().nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(1000)).correoContacto("contacto.vip1@vipmail.com").build();
        Cliente clienteRespuesta = Cliente.builder().id(1L).nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(1000)).correoContacto("contacto.vip1@vipmail.com").build();

        given(clienteService.guardaCliente(clienteParametro)).willReturn(clienteRespuesta);

        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isCreated())
                .andDo(document("cliente/post-cliente",
                        requestFields(
                                fieldWithPath("id").description("El identificador del nuevo cliente"),
                                fieldWithPath("nombre").description("El nombre del cliente"),
                                fieldWithPath("direccion").description("La dirección del cliente"),
                                fieldWithPath("correoContacto").description("La dirección de correo electrónico de contacto"),
                                fieldWithPath("numEmpleados").description("El número de personas que trabajan en las oficinas e cliente")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaCliente() throws Exception {

        Cliente clienteParametro = Cliente.builder().nombre("Cliente VIP 1").direccion("Colinas Platino").numEmpleados(String.valueOf(1000)).correoContacto("contacto.vip1@vipmail.com").build();

        mockMvc.perform(put("/cliente/{clienteId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isNoContent())
                .andDo(document("cliente/put-cliente",
                        pathParameters(
                                parameterWithName("clienteId").description("Identificador del cliente")
                        ),
                        requestFields(
                                fieldWithPath("id").description("El identificador del nuevo cliente"),
                                fieldWithPath("nombre").description("El nombre del cliente"),
                                fieldWithPath("direccion").description("La dirección del cliente"),
                                fieldWithPath("correoContacto").description("La dirección de correo electrónico de contacto"),
                                fieldWithPath("numEmpleados").description("El número de personas que trabajan en las oficinas e cliente")
                        )
                ));
    }

    @Test
    void eliminaCliente() throws Exception {
        mockMvc.perform(delete("/cliente/{clienteId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("cliente/delete-cliente",
                        pathParameters(
                                parameterWithName("clienteId").description("Identificador del cliente")
                        )));
    }
}