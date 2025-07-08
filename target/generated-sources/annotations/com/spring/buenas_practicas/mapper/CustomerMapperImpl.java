package com.spring.buenas_practicas.mapper;

import com.spring.buenas_practicas.dto.RequestCustomerDto;
import com.spring.buenas_practicas.dto.ResponseCustomerDto;
import com.spring.buenas_practicas.model.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-08T15:51:43-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.41.0.v20250213-1140, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public List<ResponseCustomerDto> toListDto(List<Customer> customer) {
        if ( customer == null ) {
            return null;
        }

        List<ResponseCustomerDto> list = new ArrayList<ResponseCustomerDto>( customer.size() );
        for ( Customer customer1 : customer ) {
            list.add( toDto( customer1 ) );
        }

        return list;
    }

    @Override
    public ResponseCustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        ResponseCustomerDto.ResponseCustomerDtoBuilder responseCustomerDto = ResponseCustomerDto.builder();

        responseCustomerDto.id( customer.getId_cliente() );
        responseCustomerDto.email( customer.getEmail() );
        responseCustomerDto.fecha_registro( customer.getFecha_registro() );
        responseCustomerDto.last_name( customer.getLast_name() );
        responseCustomerDto.name( customer.getName() );

        return responseCustomerDto.build();
    }

    @Override
    public Customer toEntity(RequestCustomerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.email( dto.getEmail() );
        customer.fecha_registro( dto.getFecha_registro() );
        customer.last_name( dto.getLast_name() );
        customer.name( dto.getName() );

        return customer.build();
    }

    @Override
    public void updateEntityFromDto(RequestCustomerDto customer, Customer entity) {
        if ( customer == null ) {
            return;
        }

        entity.setEmail( customer.getEmail() );
        entity.setFecha_registro( customer.getFecha_registro() );
        entity.setLast_name( customer.getLast_name() );
        entity.setName( customer.getName() );
    }
}
