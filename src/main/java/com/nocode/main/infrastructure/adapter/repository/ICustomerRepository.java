package com.nocode.main.infrastructure.adapter.repository;

import com.nocode.main.infrastructure.adapter.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByPhone(String phone);

    Optional<CustomerEntity> findByDocumentNumber(String documentNumber);

    Page<CustomerEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
