package com.soaint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soaint.entity.Cliente;
import com.soaint.repository.IClienteRepo;
import com.soaint.repository.IGenericRepo;
import com.soaint.service.IClienteService;

@Service
public class ClienteServiceImpl  extends CRUDImpl<Cliente, Integer> implements IClienteService {

	@Autowired
	private IClienteRepo repo;
	
	@Override
	protected IGenericRepo<Cliente, Integer> getRepo(){
		return repo;
	}

}
