package com.nocode.main.domain.port;

import com.nocode.main.domain.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ISupplierPersistencePort {

    void saveSupplier(Supplier supplier);

    Page<Supplier> findAllSuppliers(String search, int page, int size);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(String id);

    Optional<Supplier> findByDocumentNumber(String documentNumber);

    Optional<Supplier> findById(String id);

    Optional<Supplier> findByPhone(String phone);

    Optional<Supplier> findByEmail(String email);

}
