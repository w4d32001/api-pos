package com.nocode.main.infrastructure.adapter.repository;

import com.nocode.main.infrastructure.adapter.entity.ShoppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingRepository extends JpaRepository<ShoppingEntity, String> {

}
