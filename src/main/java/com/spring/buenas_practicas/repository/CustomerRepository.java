package com.spring.buenas_practicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.buenas_practicas.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
