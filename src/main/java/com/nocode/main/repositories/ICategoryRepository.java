package com.nocode.main.repositories;

import com.nocode.main.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, String> {

      Optional<CategoryEntity> findByName(String name);

      Page<CategoryEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
