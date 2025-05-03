package com.nocode.main.application.service;

import com.nocode.main.application.mapper.ProductDtoMapper;
import com.nocode.main.application.usecases.IProductService;
import com.nocode.main.domain.model.Category;
import com.nocode.main.domain.model.Product;
import com.nocode.main.domain.model.dto.ProductDto;
import com.nocode.main.domain.model.dto.request.product.CreateProduct;
import com.nocode.main.domain.model.dto.request.product.UpdateProduct;
import com.nocode.main.domain.port.ICategoryPersistencePort;
import com.nocode.main.domain.port.IProductPersistencePort;
import com.nocode.main.infrastructure.adapter.externalservices.CloudinaryService;
import com.nocode.main.infrastructure.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final IProductPersistencePort productPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final CloudinaryService cloudinaryService;

    public ProductService(final IProductPersistencePort productPersistencePort, final ICategoryPersistencePort categoryPersistencePort, final CloudinaryService cloudinaryService) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void saveProduct(CreateProduct request) {
        List<Category> categories = request.getCategories().stream()
                .map(catDto -> categoryPersistencePort.findById(catDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", catDto.getId()))
                )
                .toList();
        String image = null;
        if (request.getImageUrl() != null && !request.getImageUrl().isEmpty()) {
            image = cloudinaryService.uploadImage(request.getImageUrl());
        }

        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .barCode(UUID.randomUUID().toString())
                .categories(categories)
                .description(request.getDescription())
                .stock(0)
                .image(image)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        productPersistencePort.saveProduct(product);

    }

    @Override
    public Page<ProductDto> findAllProducts(String search, int page, int size) {
        Page<Product> products = productPersistencePort.findAllProducts(search, page, size);

        return products
                .map(ProductDtoMapper::toDto);
    }

    @Override
    public void updateProduct(String id, UpdateProduct request) {
        Product product = productPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        if (request.getImageUrl() != null) {
            String publicId = cloudinaryService.extractPublicIdFromImageUrl(product.getImage());
            if (publicId != null) {
                cloudinaryService.deleteImage(publicId);
            }

            String newImageUrl = cloudinaryService.uploadImage(request.getImageUrl());
            product.setImage(newImageUrl);
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());

        List<Category> categories = request.getCategories().stream()
                .map(catDto -> categoryPersistencePort.findById(catDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", catDto.getId()))
                )
                .collect(Collectors.toList());

        product.setCategories(categories);
        product.setUpdatedAt(LocalDateTime.now());

        productPersistencePort.updateProduct(product);

    }

    @Override
    public void deleteProduct(String id) {
        Product product = productPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        String publicId = cloudinaryService.extractPublicIdFromImageUrl(product.getImage());
        if (publicId != null) {
            cloudinaryService.deleteImage(publicId);
        }

        productPersistencePort.deleteProduct(product.getId());
    }

    @Override
    public ProductDto findById(String id) {
        Product product = productPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        return ProductDtoMapper.toDto(product);
    }
}
