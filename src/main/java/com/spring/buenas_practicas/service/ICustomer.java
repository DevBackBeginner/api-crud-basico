package com.spring.buenas_practicas.service;

import java.util.List;

import com.spring.buenas_practicas.dto.RequestCustomerDto;
import com.spring.buenas_practicas.dto.ResponseCustomerDto;

public interface ICustomer {

	List<ResponseCustomerDto> findAllCustomers();
	
	ResponseCustomerDto findCustomerById(Integer id);
	
	ResponseCustomerDto createCustomer(RequestCustomerDto customer);

	ResponseCustomerDto update(Integer id, RequestCustomerDto customer);
	
	void delete(Integer id);

}
	