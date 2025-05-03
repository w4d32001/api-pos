package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.Shopping;
import com.nocode.main.domain.port.IShoppingPersistencePort;
import com.nocode.main.infrastructure.adapter.entity.ShoppingEntity;
import com.nocode.main.infrastructure.adapter.mapper.ShoppingMapper;
import com.nocode.main.infrastructure.adapter.repository.IShoppingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ShoppingSpringJpaAdapter implements IShoppingPersistencePort {

    private final IShoppingRepository shoppingRepository;

    public ShoppingSpringJpaAdapter(final IShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    @Override
    public void createShopping(Shopping shopping) {
        ShoppingEntity entity = ShoppingMapper.toEntity(shopping);
        shoppingRepository.save(entity);
    }
}
