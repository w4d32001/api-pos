package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.Product;
import com.nocode.main.domain.port.IProductPersistencePort;
import com.nocode.main.infrastructure.adapter.entity.ProductEntity;
import com.nocode.main.infrastructure.adapter.mapper.ProductMapper;
import com.nocode.main.infrastructure.adapter.repository.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class ProductSpringJpaAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;

    public ProductSpringJpaAdapter(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);

        productRepository.save(entity);
    }

    @Override
    public Page<Product> findAllProducts(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productEntities = productRepository.findByNameContainingIgnoreCase(search, pageable);

        return productEntities
                .map(ProductMapper::toDomain);
    }

    @Override
    public void updateProduct(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);

        productRepository.save(entity);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDomain);
    }
}
