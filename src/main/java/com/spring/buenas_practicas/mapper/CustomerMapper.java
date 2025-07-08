package com.spring.buenas_practicas.mapper;

import com.spring.buenas_practicas.dto.RequestCustomerDto;
import com.spring.buenas_practicas.dto.ResponseCustomerDto;
import com.spring.buenas_practicas.model.entity.Customer;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    
	// Convertir Lista de Entidades -> Lista de Dtos (para responder)
    List<ResponseCustomerDto> toListDto(List<Customer> customer);
    
    // Convertir Entidad → DTO (para responder)
    @Mapping(target = "id", source = "id_cliente")
    ResponseCustomerDto toDto(Customer customer);
    
    // Convertir DTO → Entidad (para guardar)
    @Mapping(target = "id_cliente", ignore = true)
    Customer toEntity(RequestCustomerDto dto);
    
    @Mapping(target = "id_cliente", ignore = true)
    void updateEntityFromDto(RequestCustomerDto customer, @MappingTarget Customer entity);
}
