package com.soaint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.entity.DetalleVenta;
import com.soaint.repository.IDetalleVentaRepo;
import com.soaint.repository.IGenericRepo;
import com.soaint.service.IDetalleVentaService;

@Service
public class DetalleVentaServiceImpl extends CRUDImpl<DetalleVenta, Integer> implements IDetalleVentaService {

	@Autowired
	private IDetalleVentaRepo repo;
	
	@Override
	protected IGenericRepo<DetalleVenta, Integer> getRepo(){
		return repo;
	}
	
	

	
	
}
