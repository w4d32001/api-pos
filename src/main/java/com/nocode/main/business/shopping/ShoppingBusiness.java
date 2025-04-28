package com.nocode.main.business.shopping;

import com.nocode.main.business.shopping.dto.PurchaseDetailDto;
import com.nocode.main.business.shopping.dto.ShoppingDto;
import com.nocode.main.dtos.ProductDto;
import com.nocode.main.entities.*;
import com.nocode.main.exception.ResourceNotFoundException;
import com.nocode.main.repositories.IProductRepository;
import com.nocode.main.repositories.IPurchaseDetailRepository;
import com.nocode.main.repositories.IShoppingRepository;
import com.nocode.main.repositories.ISupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingBusiness {

    private IPurchaseDetailRepository _purchaseDetailRepo;
    private IShoppingRepository _shoppingRepo;
    private IProductRepository _productRepo;
    private ISupplierRepository _supplierRepo;

    public ShoppingBusiness(IPurchaseDetailRepository _purchaseDetailRepo, IShoppingRepository _shoppingRepo, IProductRepository _productRepo, ISupplierRepository _supplierRepo){
        this._shoppingRepo = _shoppingRepo;
        this._purchaseDetailRepo = _purchaseDetailRepo;
        this._productRepo = _productRepo;
        this._supplierRepo = _supplierRepo;
    }

    @Transactional
    public void createPurchase(ShoppingDto dto) {

        SupplierEntity supplier = _supplierRepo.findById(dto.getSupplier())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", "id", dto.getSupplier()));

        ShoppingEntity shopping = ShoppingEntity.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDateTime.now())
                .tax(dto.getTax())
                .supplier(supplier)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        _shoppingRepo.save(shopping);

        double subTotal = 0.0;
        List<PurchaseDetailEntity> purchaseDetails = new ArrayList<>();

        for (PurchaseDetailDto detailDto : dto.getDetails()) {
            ProductEntity product = _productRepo.findById(detailDto.getProduct())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", detailDto.getProduct()));

            PurchaseDetailEntity purchaseDetail = PurchaseDetailEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .shopping(shopping)
                    .product(product)
                    .purchasePrice(detailDto.getPurchasePrice())
                    .salePrice(detailDto.getSalePrice())
                    .amount(detailDto.getAmount())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            purchaseDetails.add(purchaseDetail);

            subTotal += detailDto.getPurchasePrice() * detailDto.getAmount();
        }

        double total = subTotal + (subTotal * (dto.getTax() / 100));

        shopping.setSubTotal(subTotal);
        shopping.setTotal(total);
        shopping.setPurchaseDetail(purchaseDetails);

        _shoppingRepo.save(shopping);

        _purchaseDetailRepo.saveAll(purchaseDetails);
    }



}
