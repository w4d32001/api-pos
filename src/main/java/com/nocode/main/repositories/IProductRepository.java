package com.nocode.main.repositories;

import com.nocode.main.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, String> {

    Page<ProductEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
