package com.nocode.main.infrastructure.adapter.repository;

import com.nocode.main.infrastructure.adapter.entity.PurchaseDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, String> {
}
