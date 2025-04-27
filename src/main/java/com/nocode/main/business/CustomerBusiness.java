package com.nocode.main.business;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nocode.main.dtos.CustomerDto;
import com.nocode.main.entities.CustomerEntity;
import com.nocode.main.exception.ConflictException;
import com.nocode.main.repositories.ICustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerBusiness {

      @Autowired
      private ICustomerRepository _repo;

      public Page<CustomerDto> findAllCustomers(String search, int page, int size) {
            
            Pageable pageable = PageRequest.of(page, size);

            Page<CustomerEntity> customerEntityPage = _repo.findByNameContainingIgnoreCase(search, pageable);

            return customerEntityPage.map(
              customer -> {
                  CustomerDto dto = new CustomerDto();
                  dto.setId(customer.getId());
                  dto.setName(customer.getName());
                  dto.setDocumentType(customer.getDocumentType());
                  dto.setDocumentNumber(customer.getDocumentNumber());
                  dto.setPhone(customer.getPhone());
                  dto.setCreatedAt(customer.getCreatedAt());
                  dto.setUpdatedAt(customer.getUpdatedAt());
                  return  dto;
              }    
            );
      }

      @Transactional
      public void createCustomer(CustomerDto dto) {
            
            _repo.findByPhone(dto.getPhone())
                  .ifPresent( n -> {
                        throw  new ConflictException("El cliente con el teléfono: '" + dto.getPhone() + "' ya existe.");
                  } );

            _repo.findByDocumentNumber(dto.getDocumentNumber())
                  .ifPresent( n -> {
                        throw  new ConflictException("El cliente con el número de documento: '" + dto.getDocumentNumber() + "' ya existe.");
                  } );

            CustomerEntity entity = CustomerEntity.builder()
                  .id(UUID.randomUUID().toString())
                  .name(dto.getName())
                  .documentType(dto.getDocumentType())
                  .documentNumber(dto.getDocumentNumber())
                  .phone(dto.getPhone())
                  .createdAt(LocalDateTime.now())
                  .updatedAt(LocalDateTime.now())
                  .build();

           _repo.save(entity);
      }

      @Transactional
      public void updateCustomer(String id, CustomerDto dto) {
            
            CustomerEntity entity = _repo.findById(id)
                  .orElseThrow(() -> new ConflictException("El cliente con el id: '" + id + "' no existe."));

            _repo.findByDocumentNumber(dto.getDocumentNumber())
            .filter(d -> !d.getId().equals(id))
                  .ifPresent( n -> {
                        throw  new ConflictException("El cliente con el número de documento: '" + dto.getDocumentNumber() + "' ya existe.");
                  } );
            
            _repo.findByPhone(dto.getPhone())
                  .filter(d -> !d.getId().equals(id))
                  .ifPresent( n -> {
                        throw  new ConflictException("El cliente con el teléfono: '" + dto.getPhone() + "' ya existe.");
                  } );

            entity.setName(dto.getName());
            entity.setDocumentType(dto.getDocumentType());
            entity.setDocumentNumber(dto.getDocumentNumber());
            entity.setPhone(dto.getPhone());
            entity.setUpdatedAt(LocalDateTime.now());

           _repo.save(entity);
      }

      @Transactional
      public void deleteCustomer(String id) {
            
            CustomerEntity entity = _repo.findById(id)
                  .orElseThrow(() -> new ConflictException("El cliente con el id: '" + id + "' no existe."));

            _repo.delete(entity);
      }

}
