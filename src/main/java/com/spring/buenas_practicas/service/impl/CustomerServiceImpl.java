package com.spring.buenas_practicas.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.buenas_practicas.dto.RequestCustomerDto;
import com.spring.buenas_practicas.dto.ResponseCustomerDto;
import com.spring.buenas_practicas.mapper.CustomerMapper;
import com.spring.buenas_practicas.model.entity.Customer;
import com.spring.buenas_practicas.repository.CustomerRepository;
import com.spring.buenas_practicas.service.ICustomer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomer{

	private final CustomerRepository customerRepo;
	private final CustomerMapper customerMapper;
	
	@Transactional(readOnly = true)
	@Override
	public List<ResponseCustomerDto> findAllCustomers() {
		List<Customer> customers = customerRepo.findAll();
		return customerMapper.toListDto(customers);
	}

	@Transactional(readOnly = true)
	@Override
	public ResponseCustomerDto findCustomerById(Integer id) {
		Customer customer = customerRepo.findById(id).orElseThrow(() 
				-> new NoSuchElementException("Customer no encontrado con el id " + id));
		ResponseCustomerDto customerDto = customerMapper.toDto(customer);
		return customerDto;
	}
	
	@Transactional
	@Override
	public ResponseCustomerDto createCustomer(RequestCustomerDto customerDto) {
		if (customerDto == null) {
		    throw new IllegalArgumentException("No se han recibido los parametros necesarios");
		}
	
		Customer customer = customerMapper.toEntity(customerDto);
			
		Customer save = customerRepo.save(customer);
		
		return customerMapper.toDto(save);
	}
	
	@Transactional
	@Override
	public ResponseCustomerDto update(Integer id, RequestCustomerDto customerDto) {
		Customer existingCustomer = customerRepo.findById(id).orElseThrow(() 
				-> new NoSuchElementException("Customer no encontrado con el id " + id));
		
		if (customerDto == null) {
			throw new IllegalArgumentException("No se han recibido los parametros necesarios");
		}
		
		 customerMapper.updateEntityFromDto(customerDto, existingCustomer);
		 
		 Customer update = customerRepo.save(existingCustomer);
		 
		 return customerMapper.toDto(update);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		customerRepo.findById(id).orElseThrow(()
			 -> new NoSuchElementException("No existe un cliente con el id " + id));
		customerRepo.deleteById(id);
	}
}
