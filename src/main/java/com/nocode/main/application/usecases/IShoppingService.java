package com.nocode.main.application.usecases;

import com.nocode.main.domain.model.dto.request.shopping.CreateShopping;

public interface IShoppingService {

    void saveShopping(CreateShopping request);

}
