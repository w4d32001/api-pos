package com.nocode.main.domain.port;

import com.nocode.main.domain.model.Shopping;

public interface IShoppingPersistencePort {

    void createShopping(Shopping shopping);

}
