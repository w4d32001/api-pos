package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.Shopping;
import com.nocode.main.infrastructure.adapter.entity.ShoppingEntity;

import java.util.Collections;
import java.util.stream.Collectors;

public class ShoppingMapper {

    public static Shopping toDomain(ShoppingEntity entity) {
        return Shopping.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .voucherType(entity.getVoucherType())
                .tax(entity.getTax())
                .supplier(SupplierMapper.toDomain(entity.getSupplier()))
                .subTotal(entity.getSubTotal())
                .total(entity.getTotal())
                .purchaseDetail(
                        entity.getPurchaseDetail() != null ?
                                entity.getPurchaseDetail().stream()
                                        .map(PurchaseDetailMapper::toDomain)  // Aquí usamos la referencia de método correctamente
                                        .collect(Collectors.toList())
                                : Collections.emptyList()
                )
                .build();
    }

    public static ShoppingEntity toEntity(Shopping domain) {
        return ShoppingEntity.builder()
                .id(domain.getId())
                .date(domain.getDate())
                .voucherType(domain.getVoucherType())
                .tax(domain.getTax())
                .supplier(SupplierMapper.toEntity(domain.getSupplier()))
                .subTotal(domain.getSubTotal())
                .total(domain.getTotal())
                .purchaseDetail(domain.getPurchaseDetail() != null ?
                        domain.getPurchaseDetail().stream()
                                .map(PurchaseDetailMapper::toEntity)  // Asegúrate de que toEntity esté correcto
                                .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }
}
