package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.PurchaseDetail;
import com.nocode.main.domain.port.IPurchaseDetailPersistencePort;
import com.nocode.main.infrastructure.adapter.entity.PurchaseDetailEntity;
import com.nocode.main.infrastructure.adapter.mapper.PurchaseDetailMapper;
import com.nocode.main.infrastructure.adapter.repository.IPurchaseDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PurchaseDetailSpringJpaAdapter implements IPurchaseDetailPersistencePort {

    private final IPurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetailSpringJpaAdapter(final IPurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    @Override
    public void savePurchaseDetail(List<PurchaseDetail> purchaseDetails) {
        List<PurchaseDetailEntity> entities = PurchaseDetailMapper.toEntityList(purchaseDetails);
        purchaseDetailRepository.saveAll(entities);
    }
}
