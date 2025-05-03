package com.nocode.main.application.service;

import com.nocode.main.application.usecases.IShoppingService;
import com.nocode.main.domain.model.Product;
import com.nocode.main.domain.model.PurchaseDetail;
import com.nocode.main.domain.model.Shopping;
import com.nocode.main.domain.model.Supplier;
import com.nocode.main.domain.model.dto.PurchaseDetailDto;
import com.nocode.main.domain.model.dto.request.shopping.CreatePurchaseDetail;
import com.nocode.main.domain.model.dto.request.shopping.CreateShopping;
import com.nocode.main.domain.port.IProductPersistencePort;
import com.nocode.main.domain.port.IPurchaseDetailPersistencePort;
import com.nocode.main.domain.port.IShoppingPersistencePort;
import com.nocode.main.domain.port.ISupplierPersistencePort;
import com.nocode.main.infrastructure.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingService implements IShoppingService {

    private final IShoppingPersistencePort shoppingPersistencePort;

    private final IPurchaseDetailPersistencePort purchaseDetailPersistencePort;

    private final IProductPersistencePort productPersistencePort;

    private final ISupplierPersistencePort supplierPersistencePort;

    public ShoppingService(
            final IShoppingPersistencePort shoppingPersistencePort,
            final IPurchaseDetailPersistencePort purchaseDetailPersistencePort,
            final IProductPersistencePort productPersistencePort,
            final ISupplierPersistencePort supplierPersistencePort
    ) {
        this.shoppingPersistencePort = shoppingPersistencePort;
        this.purchaseDetailPersistencePort = purchaseDetailPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.supplierPersistencePort = supplierPersistencePort;
    }

    @Override
    public void saveShopping(CreateShopping request) {

        Supplier supplier = supplierPersistencePort.findById(request.getSupplier())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", "id", request.getSupplier()));

        Shopping shopping = Shopping.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDateTime.now())
                .tax(request.getTax())
                .supplier(supplier)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        shoppingPersistencePort.createShopping(shopping);

        double subTotal = 0.0;
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();

        for (CreatePurchaseDetail detailDto : request.getDetails()) {
            Product product = productPersistencePort.findById(detailDto.getProduct())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", detailDto.getProduct()));

            PurchaseDetail existingDetail = purchaseDetails.stream()
                    .filter(detail -> detail.getProduct().getId().equals(product.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingDetail != null) {
                existingDetail.setAmount(existingDetail.getAmount() + detailDto.getAmount());
                subTotal += existingDetail.getPurchasePrice() * existingDetail.getAmount();
            } else {
                PurchaseDetail purchaseDetail = PurchaseDetail.builder()
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
                subTotal += purchaseDetail.getPurchasePrice() * purchaseDetail.getAmount();
            }

            product.setStock(product.getStock() + detailDto.getAmount());
            productPersistencePort.saveProduct(product);
        }

        double total = subTotal + (subTotal * (request.getTax() / 100));

        shopping.setSubTotal(subTotal);
        shopping.setTotal(total);
        shopping.setPurchaseDetail(purchaseDetails);

        purchaseDetailPersistencePort.savePurchaseDetail(purchaseDetails);
    }
}
