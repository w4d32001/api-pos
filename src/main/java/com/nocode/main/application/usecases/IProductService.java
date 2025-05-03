package com.nocode.main.application.usecases;

import com.nocode.main.domain.model.dto.ProductDto;
import com.nocode.main.domain.model.dto.request.product.CreateProduct;
import com.nocode.main.domain.model.dto.request.product.UpdateProduct;
import org.springframework.data.domain.Page;

public interface IProductService {
    void saveProduct(CreateProduct request);

    Page<ProductDto> findAllProducts(String search, int page, int size);

    void updateProduct(String id, UpdateProduct request);

    void deleteProduct(String id);

    ProductDto findById(String id);
}
