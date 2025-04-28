package com.nocode.main.services.shopping;

import com.nocode.main.business.shopping.ShoppingBusiness;
import com.nocode.main.business.shopping.dto.PurchaseDetailDto;
import com.nocode.main.business.shopping.dto.ShoppingDto;
import com.nocode.main.commos.ApiResponse;
import com.nocode.main.commos.ResponseBuilder;
import com.nocode.main.services.shopping.request.CreateShoppingDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/purchases")
public class ShoppingController {

    private ShoppingBusiness _shopping;

    public ShoppingController(ShoppingBusiness _shopping){
        this._shopping = _shopping;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createShopping(@Valid @RequestBody CreateShoppingDto request){

        List<PurchaseDetailDto> mappedDetails = request.getDetails().stream().map(detail ->
                PurchaseDetailDto.builder()
                        .product(detail.getProduct())
                        .purchasePrice(detail.getPurchasePrice())
                        .salePrice(detail.getSalePrice())
                        .amount(detail.getAmount())
                        .build()
        ).collect(Collectors.toList());

        ShoppingDto dto = ShoppingDto.builder()
                .tax(request.getTax())
                .supplier(request.getSupplier())
                .voucherType(request.getVoucherType())
                .details(mappedDetails)
                .build();

        _shopping.createPurchase(dto);


        return ResponseBuilder.created("Compra generada exitosamente");

    }

}
