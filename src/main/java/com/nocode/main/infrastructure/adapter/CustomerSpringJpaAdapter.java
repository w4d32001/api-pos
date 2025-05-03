package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.Customer;
import com.nocode.main.domain.port.ICustomerPersistencePort;
import com.nocode.main.infrastructure.adapter.entity.CustomerEntity;
import com.nocode.main.infrastructure.adapter.mapper.CustomerMapper;
import com.nocode.main.infrastructure.adapter.repository.ICustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CustomerSpringJpaAdapter implements ICustomerPersistencePort {

    private final ICustomerRepository customerRepository;

    public CustomerSpringJpaAdapter(final ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {

        CustomerEntity customerEntity = CustomerMapper.toEntity(customer);

        customerRepository.save(customerEntity);

    }

    @Override
    public Page<Customer> findAllCustomers(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerEntities = customerRepository.findByNameContainingIgnoreCase(search, pageable);

        return customerEntities.map(CustomerMapper::toDomain);

    }

    @Override
    public void updateCustomer(Customer customer) {

        CustomerEntity customerEntity = CustomerMapper.toEntity(customer);

        customerRepository.save(customerEntity);

    }

    @Override
    public void deleteCustomer(String id) {

        customerRepository.deleteById(id);

    }

    @Override
    public Optional<Customer> findByDocumentNumber(String documentNumber) {
        return customerRepository.findByDocumentNumber(documentNumber)
                .map(CustomerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        return customerRepository.findByPhone(phone)
                .map(CustomerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toDomain);
    }
}
