package com.nocode.main.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nocode.main.entities.SupplierEntity;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, String> {
      Optional<SupplierEntity> findByPhone(String phone);

      Optional<SupplierEntity> findByDocumentNumber(String documentNumber);

      Optional<SupplierEntity> findByEmail(String email);

      Page<SupplierEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
