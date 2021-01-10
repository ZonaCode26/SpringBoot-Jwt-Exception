package com.soaint.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

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

import com.soaint.controller.VentaController;
import com.soaint.entity.Cliente;
import com.soaint.entity.Venta;
import com.soaint.service.IVentaService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VentaControllerTests {

	@InjectMocks
    VentaController VentaController;
	
	@Mock
	IVentaService service;

	
	@Test
    void testGetVenta() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    
        Cliente cliente = new Cliente(1, "Alexi", "Ae","12345543","943942005", "howtodoinjava@gmail.com");
        
        Venta entidad = new Venta(1, cliente, LocalDateTime.now());
        
        
        when(service.listarPorId(1)).thenReturn(entidad);
         
        ResponseEntity<Venta>   responseEntity = VentaController.listarPorId(1);
         
        assertThat(responseEntity.getBody().getCliente()).isEqualTo(null);//false
        assertThat(responseEntity.getBody().getIdVenta()).isEqualTo(2);//false
        assertThat(responseEntity.getStatusCode()).isEqualTo(404);//false
    }
	
	
	

}
