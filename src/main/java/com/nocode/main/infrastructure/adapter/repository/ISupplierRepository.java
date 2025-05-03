package com.nocode.main.infrastructure.adapter.repository;

import com.nocode.main.domain.model.Supplier;
import com.nocode.main.infrastructure.adapter.entity.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, String> {

    Page<SupplierEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<SupplierEntity> findByDocumentNumber(String documentNumber);

    Optional<SupplierEntity> findByPhone(String phone);

    Optional<SupplierEntity> findByEmail(String email);

}
