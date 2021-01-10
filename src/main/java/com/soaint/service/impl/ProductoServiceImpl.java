package com.soaint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.entity.Producto;
import com.soaint.repository.IGenericRepo;
import com.soaint.repository.IProductoRepo;
import com.soaint.service.IProductoService;

@Service
public class ProductoServiceImpl extends CRUDImpl<Producto, Integer> implements IProductoService {

	@Autowired
	private IProductoRepo repo;
	
	@Override
	protected IGenericRepo<Producto, Integer> getRepo(){
		return repo;
	}
}
