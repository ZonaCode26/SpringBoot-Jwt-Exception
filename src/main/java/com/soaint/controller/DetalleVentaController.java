package com.soaint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.service.IDetalleVentaService;


@RestController
@RequestMapping("/detalleventas")
//@CrossOrigin
public class DetalleVentaController {

	
	
	@Autowired	
	private IDetalleVentaService service;

}
