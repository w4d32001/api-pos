package com.nocode.main.repositories;

import com.nocode.main.entities.ShoppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingRepository extends JpaRepository<ShoppingEntity, String> {
}
