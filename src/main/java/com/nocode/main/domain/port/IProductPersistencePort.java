package com.nocode.main.domain.port;

import com.nocode.main.domain.model.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IProductPersistencePort {

    void saveProduct(Product product);

    Page<Product> findAllProducts(String search, int page, int size);

    void updateProduct(Product product);

    void deleteProduct(String id);

    Optional<Product> findById(String id);

}
