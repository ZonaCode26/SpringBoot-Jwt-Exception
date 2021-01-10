package com.soaint.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soaint.entity.Venta;
import com.soaint.exception.ModeloNotFoundException;
import com.soaint.service.IVentaService;

@RestController
@RequestMapping("/ventas")
//@CrossOrigin
public class VentaController {

	@Autowired	
	private IVentaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> listar() throws Exception{
		List<Venta> lista = service.listar();
		return new ResponseEntity<List<Venta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Venta obj = service.listarPorId(id);
		if(obj == null ) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registrar(@Valid @RequestBody Venta entity) throws Exception{
		Venta obj = service.registrar(entity);
		
		//localhost:8080/pacientes/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venta> modificar(@Valid @RequestBody Venta entity) throws Exception{
		Venta obj = service.modificar(entity);
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping(path =  "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Venta obj = service.listarPorId(id);
		if(obj.getIdVenta() == null ) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
