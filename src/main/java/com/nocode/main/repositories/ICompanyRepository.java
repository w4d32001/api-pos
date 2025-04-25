package com.nocode.main.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nocode.main.entities.CompanyEntity;
@Repository
public interface ICompanyRepository extends JpaRepository<CompanyEntity, String> {
      Page<CompanyEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
