package com.nocode.main.repositories;

import com.nocode.main.entities.PurchaseDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, String> {
}
