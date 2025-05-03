package com.nocode.main.domain.port;

import com.nocode.main.domain.model.PurchaseDetail;

import java.util.List;

public interface IPurchaseDetailPersistencePort {

    void savePurchaseDetail(List<PurchaseDetail> purchaseDetails);

}
