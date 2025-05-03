package com.nocode.main.application.usecases;

import com.nocode.main.domain.model.dto.CustomerDto;
import com.nocode.main.domain.model.dto.request.customer.CreateCustomer;
import com.nocode.main.domain.model.dto.request.customer.UpdateCustomer;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ICustomerService {
    void createCustomer(CreateCustomer request);

    Page<CustomerDto> findAllCustomers(String search, int page, int size);

    void updateCustomer(String id, UpdateCustomer request);

    void deleteCustomer(String id);

    CustomerDto findByDocumentNumber(String documentNumber);

    CustomerDto findByPhone(String phone);

    CustomerDto findById(String id);
}
