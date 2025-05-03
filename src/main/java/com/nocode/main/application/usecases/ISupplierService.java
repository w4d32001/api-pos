package com.nocode.main.application.usecases;

import com.nocode.main.domain.model.Supplier;
import com.nocode.main.domain.model.dto.SupplierDto;
import com.nocode.main.domain.model.dto.request.supplier.CreateSupplier;
import com.nocode.main.domain.model.dto.request.supplier.UpdateSupplier;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ISupplierService {
    void saveSupplier(CreateSupplier request);

    Page<SupplierDto> findAllSuppliers(String search, int page, int size);

    void updateSupplier(String id, UpdateSupplier request);

    void deleteSupplier(String id);

    SupplierDto findByDocumentNumber(String documentNumber);

    SupplierDto findById(String id);

    SupplierDto findByPhone(String phone);

    SupplierDto findByEmail(String email);
}
