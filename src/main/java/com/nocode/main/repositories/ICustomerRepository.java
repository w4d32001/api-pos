package com.nocode.main.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nocode.main.entities.CustomerEntity;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, String> {

      Optional<CustomerEntity> findByPhone(String phone);

      Optional<CustomerEntity> findByDocumentNumber(String documentNumber);

      Page<CustomerEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
