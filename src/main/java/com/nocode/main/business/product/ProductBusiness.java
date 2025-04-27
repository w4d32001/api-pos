package com.nocode.main.business.product;

import com.nocode.main.business.CloudinaryService;
import com.nocode.main.business.product.dto.ResponseProductDto;
import com.nocode.main.dtos.CategoryDto;
import com.nocode.main.dtos.ProductDto;
import com.nocode.main.entities.CategoryEntity;
import com.nocode.main.entities.ProductEntity;
import com.nocode.main.exception.ResourceNotFoundException;
import com.nocode.main.repositories.ICategoryRepository;
import com.nocode.main.repositories.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductBusiness {

    @Autowired
    private IProductRepository _repo;

    @Autowired
    private CloudinaryService _cloudinary;

    @Autowired
    private ICategoryRepository _category;

    public Page<ResponseProductDto> findAllProducts(String search, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ProductEntity> productEntityPage = _repo.findByNameContainingIgnoreCase(search, pageable);

        return productEntityPage.map(
                product -> {
                    ResponseProductDto dto = new ResponseProductDto();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setCategories(
                            product.getCategories()
                                    .stream()
                                    .map(category -> CategoryDto.builder()
                                            .id(category.getId())
                                            .name(category.getName())
                                            .description(category.getDescription())
                                            .updatedAt(category.getUpdatedAt())
                                            .build())
                                    .collect(Collectors.toList())
                    );
                    dto.setDescription(product.getDescription());
                    dto.setImage(product.getImage());
                    dto.setStock(product.getStock());
                    dto.setBarCode(product.getBarCode());
                    dto.setUpdatedAt(product.getUpdatedAt());

                    return dto;
                }
        );

    }

    @Transactional
    public void createProduct(ProductDto dto) {

        List<CategoryEntity> categories = dto.getCategories().stream()
                .map(catDto -> _category.findById(catDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", catDto.getId()))
                )
                .toList();

        ProductEntity entity = ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.getName())
                .barCode(UUID.randomUUID().toString())
                .categories(categories)
                .stock(dto.getStock())
                .image(_cloudinary.uploadImage(dto.getImageUrl()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        _repo.save(entity);
    }

    @Transactional
    public void updateProduct(String productId, ProductDto dto) {
        ProductEntity productEntity = _repo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productId));

        if (dto.getImageUrl() != null) {
            String publicId = _cloudinary.extractPublicIdFromImageUrl(productEntity.getImage());
            if (publicId != null) {
                _cloudinary.deleteImage(publicId);
            }

            String newImageUrl = _cloudinary.uploadImage(dto.getImageUrl());
            productEntity.setImage(newImageUrl);
        }

        productEntity.setName(dto.getName());
        productEntity.setStock(dto.getStock());
        productEntity.setDescription(dto.getDescription());

        List<CategoryEntity> categories = dto.getCategories().stream()
                .map(catDto -> _category.findById(catDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", catDto.getId()))
                )
                .collect(Collectors.toList());

        productEntity.setCategories(categories);
        productEntity.setUpdatedAt(LocalDateTime.now());

        _repo.save(productEntity);

    }


    @Transactional
    public void deleteProduct(String productId) {
        ProductEntity productEntity = _repo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productId));

        String publicId = _cloudinary.extractPublicIdFromImageUrl(productEntity.getImage());
        if (publicId != null) {
            _cloudinary.deleteImage(publicId);
        }

        _repo.delete(productEntity);
    }
}
