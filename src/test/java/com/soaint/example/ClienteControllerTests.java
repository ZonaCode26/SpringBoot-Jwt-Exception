package com.soaint.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.soaint.controller.ClienteController;
import com.soaint.entity.Cliente;
import com.soaint.service.IClienteService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClienteControllerTests {

	@InjectMocks
    ClienteController clienteController;
	
	@Mock
	IClienteService service;
	
	@Test
    void testAddCliente() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(service.registrar(any(Cliente.class))).thenReturn(new Cliente(1, "Alexi", "Ae","12345543","943942005", "howtodoinjava@gmail.com"));
         
        Cliente employee = new Cliente(1, "Alexi", "Ae","12345543","943942005", "howtodoinjava@gmail.com");
        ResponseEntity<Void> responseEntity = clienteController.registrar(employee);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

	@Test
    void testUpdateCliente() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(service.modificar(any(Cliente.class))).thenReturn(new Cliente(1, "Alexi", "Ae","12345543","943942005", "alx.27@outlook.com"));
         
        Cliente employee = new Cliente(1, "Alexi", "Ae","12345543","943942005", "alek.ae27@gmail.com");
        ResponseEntity<Cliente> responseEntity = clienteController.modificar(employee);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertFalse(responseEntity.getBody().getEmail().equals("alek.ae27@gmail.com"));
    }

	
	
	@Test
    void testGetCliente() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(service.listarPorId(1)).thenReturn(new Cliente(1, "Alexi", "Ae","12345543","943942005", "howtodoinjava@gmail.com"));
         
        ResponseEntity<Cliente>   responseEntity = clienteController.listarPorId(1);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getIdCliente()).isEqualTo(1);
    }
	
	
	@Test
    void testGetAllCliente() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        List<Cliente> lista = new ArrayList<>();
        lista.add(new Cliente(1, "Alexi", "Ae","12345543","943942005", "howtodoinjava@gmail.com"));
        when(service.listar()).thenReturn( lista);
         
        ResponseEntity<List<Cliente>>   responseEntity = clienteController.listar();
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertFalse(responseEntity.getBody().isEmpty());
    }
	
	

}
