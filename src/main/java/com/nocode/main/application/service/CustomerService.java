package com.nocode.main.application.service;

import com.nocode.main.application.mapper.CustomerDtoMapper;
import com.nocode.main.application.usecases.ICustomerService;
import com.nocode.main.domain.model.Customer;
import com.nocode.main.domain.model.dto.CustomerDto;
import com.nocode.main.domain.model.dto.request.customer.CreateCustomer;
import com.nocode.main.domain.model.dto.request.customer.UpdateCustomer;
import com.nocode.main.domain.port.ICustomerPersistencePort;
import com.nocode.main.infrastructure.shared.exception.ConflictException;
import com.nocode.main.infrastructure.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerPersistencePort customerPersistencePort;

    @Autowired
    public CustomerService(ICustomerPersistencePort customerPersistencePort) {
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public void createCustomer(CreateCustomer request) {
        customerPersistencePort.findByPhone(request.getPhone())
                .ifPresent(n -> {
                    throw new ConflictException("El cliente con el teléfono: '" + request.getPhone() + "' ya existe.");
                });

        customerPersistencePort.findByDocumentNumber(request.getDocumentNumber())
                .ifPresent(n -> {
                    throw new ConflictException("El cliente con el número de documento: '" + request.getDocumentNumber() + "' ya existe.");
                });

        Customer customer = Customer.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .phone(request.getPhone())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        customerPersistencePort.saveCustomer(customer);
    }

    @Override
    public Page<CustomerDto> findAllCustomers(String search, int page, int size) {

        Page<Customer> customerPage = customerPersistencePort.findAllCustomers(search, page, size);

        return customerPage.map(CustomerDtoMapper::toDto);

    }

    @Override
    public void updateCustomer(String id, UpdateCustomer request) {

        Customer customer = customerPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id)
                );

        customerPersistencePort.findByDocumentNumber(request.getDocumentNumber())
                .filter(d -> !d.getId().equals(id))
                .ifPresent(n -> {
                    throw new ConflictException("El cliente con el número de documento: '" + request.getDocumentNumber() + "' ya existe.");
                });

        customerPersistencePort.findByPhone(request.getPhone())
                .filter(d -> !d.getId().equals(id))
                .ifPresent(n -> {
                    throw new ConflictException("El cliente con el teléfono: '" + request.getPhone() + "' ya existe.");
                });

        customer.setName(request.getName());
        customer.setDocumentType(request.getDocumentType());
        customer.setDocumentNumber(request.getDocumentNumber());
        customer.setPhone(request.getPhone());
        customer.setUpdatedAt(LocalDateTime.now());

        customerPersistencePort.updateCustomer(customer);

    }

    @Override
    public void deleteCustomer(String id) {
        customerPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id)
                );

        customerPersistencePort.deleteCustomer(id);
    }

    @Override
    public CustomerDto findByDocumentNumber(String documentNumber) {
        Customer customer = customerPersistencePort.findByDocumentNumber(documentNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "documentNumber", documentNumber)
                );
        return CustomerDtoMapper.toDto(customer);
    }

    @Override
    public CustomerDto findByPhone(String phone) {
        Customer customer = customerPersistencePort.findByPhone(phone).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "phone", phone)
        );
        return CustomerDtoMapper.toDto(customer);
    }

    @Override
    public CustomerDto findById(String id) {
        Customer customer = customerPersistencePort.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id)
        );
        return CustomerDtoMapper.toDto(customer);
    }
}
