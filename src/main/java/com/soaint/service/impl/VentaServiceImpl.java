package com.soaint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.entity.Venta;
import com.soaint.repository.IGenericRepo;
import com.soaint.repository.IVentaRepo;
import com.soaint.service.IVentaService;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {

	@Autowired
	private IVentaRepo repo;
	
	@Override
	protected IGenericRepo<Venta, Integer> getRepo(){
		return repo;
	}
}
