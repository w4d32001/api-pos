package com.nocode.main.domain.port;

import com.nocode.main.domain.model.Customer;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ICustomerPersistencePort {
    void saveCustomer(Customer customer);

    Page<Customer> findAllCustomers(String search, int page, int size);

    void updateCustomer(Customer customer);

    void deleteCustomer(String id);

    Optional<Customer> findByDocumentNumber(String documentNumber);

    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findById(String id);
}
