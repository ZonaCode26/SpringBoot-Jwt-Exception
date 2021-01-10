package com.soaint.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

import com.soaint.controller.ProductoController;
import com.soaint.entity.Producto;
import com.soaint.service.IProductoService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductoControllerTests {

	@InjectMocks
    ProductoController ProductoController;
	
	@Mock
	IProductoService service;

	
	@Test
    void testGetProducto() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    
        Producto entidad = new Producto(1, "Celular", 5000.00);
        when(service.listarPorId(1)).thenReturn(entidad);
         
        ResponseEntity<Producto>   responseEntity = ProductoController.listarPorId(1);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getBody().getIdProducto()).isEqualTo(2);
    }
	
	
	

}
