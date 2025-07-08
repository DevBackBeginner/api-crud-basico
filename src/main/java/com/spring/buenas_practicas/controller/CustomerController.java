package com.spring.buenas_practicas.controller;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.buenas_practicas.dto.MessageResponse;
import com.spring.buenas_practicas.dto.RequestCustomerDto;
import com.spring.buenas_practicas.dto.ResponseCustomerDto;
import com.spring.buenas_practicas.service.impl.CustomerServiceImpl;
import com.spring.buenas_practicas.util.ResponseHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CustomerController {
	
	private final CustomerServiceImpl customerService;
	
	 @GetMapping("/customers")
	 public ResponseEntity<MessageResponse<List<ResponseCustomerDto>>> showCustomers(){
		 List<ResponseCustomerDto> customersDto = customerService.findAllCustomers();
		 
		 String message = customersDto.isEmpty() ? "Lista vacia" : "Lista de clientes";
		 
		 return ResponseHelper.ok(message, customersDto);
	 }
	 
	 @GetMapping("/customer/{id}")
	 public ResponseEntity<MessageResponse<ResponseCustomerDto>> showById(@PathVariable Integer id) {
	    try {
	        ResponseCustomerDto customer = customerService.findCustomerById(id);
	        return ResponseHelper.ok("Cliente encontrado", customer);
	        
	    } catch (NoSuchElementException e) {
	        return ResponseHelper.notFound(e.getMessage(), null);
	        
	    } catch (DataAccessException e) {
	        return ResponseHelper.internalError("Error interno del servidor, por favor inténtelo más tarde", null);
	    }
	 }
	
	@PostMapping("/create")
	public ResponseEntity<MessageResponse<ResponseCustomerDto>> create(@RequestBody RequestCustomerDto customerDto) {
		try {
			ResponseCustomerDto customer = customerService.createCustomer(customerDto);
			return ResponseHelper.create("Cliente creado exitosamente", customer);
			
		} catch (IllegalArgumentException e) {
			return ResponseHelper.badRequest(e.getMessage(), null);
			
		}  catch (DataAccessException e) {
			log.error(e.getMessage());
	        return ResponseHelper.internalError("Error interno del servidor, por favor inténtelo más tarde", null);
	    }
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<MessageResponse<ResponseCustomerDto>> update(@PathVariable Integer id, @RequestBody RequestCustomerDto customerDto) {
	    try {
	    	
	        ResponseCustomerDto updateCustomer = customerService.update(id, customerDto);
	        return ResponseHelper.ok("Cliente actualizado correctamente", updateCustomer);

	    } catch (NoSuchElementException e) {
	        return ResponseHelper.notFound(e.getMessage(), null);

	    } catch (IllegalArgumentException e) {
	        return ResponseHelper.badRequest("Error: el contenido no puede estar vacío", null);

	    } catch (DataAccessException e) {
	        log.error("Error al acceder a la base de datos", e);
	        return ResponseHelper.internalError("Error interno del servidor, por favor inténtelo más tarde", null);
	    }
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	    try {			        
	        customerService.delete(id);
	        return ResponseHelper.noContent();
	        
	    } catch (NoSuchElementException e) {
	        return ResponseHelper.notFound(e.getMessage(), null);
	        
	    } catch (DataAccessException e) {
	        return ResponseHelper.internalError("Error interno del servidor, por favor inténtelo más tarde", null);
	    }
	}
}