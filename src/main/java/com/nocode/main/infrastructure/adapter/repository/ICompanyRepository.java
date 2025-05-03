package com.nocode.main.infrastructure.adapter.repository;

import com.nocode.main.infrastructure.adapter.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<CompanyEntity, String> {
    Page<CompanyEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
