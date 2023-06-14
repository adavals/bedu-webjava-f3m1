package org.bedu.java.backend.postwork.controller;

import org.bedu.java.backend.postwork.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteControllerIntegrationTest {


    private TestRestTemplate restTemplate;
    @Autowired
    public ClienteControllerIntegrationTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    void getCliente() {
        ResponseEntity<Cliente> response = restTemplate.getForEntity("/cliente/1", Cliente.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getId(), equalTo(1L));
    }
}