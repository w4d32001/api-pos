package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.Supplier;
import com.nocode.main.domain.port.ISupplierPersistencePort;
import com.nocode.main.infrastructure.adapter.entity.SupplierEntity;
import com.nocode.main.infrastructure.adapter.mapper.SupplierMapper;
import com.nocode.main.infrastructure.adapter.repository.ISupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class SupplierSpringJpaAdapter implements ISupplierPersistencePort {

    private final ISupplierRepository supplierRepository;

    public SupplierSpringJpaAdapter(final ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        SupplierEntity entity = SupplierMapper.toEntity(supplier);

        supplierRepository.save(entity);
    }

    @Override
    public Page<Supplier> findAllSuppliers(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SupplierEntity> supplierEntities = supplierRepository.findByNameContainingIgnoreCase(search, pageable);

        return supplierEntities.map(SupplierMapper::toDomain);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        SupplierEntity entity = SupplierMapper.toEntity(supplier);

        supplierRepository.save(entity);
    }

    @Override
    public void deleteSupplier(String id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Optional<Supplier> findByDocumentNumber(String documentNumber) {
        return supplierRepository.findByDocumentNumber(documentNumber)
                .map(SupplierMapper::toDomain);
    }

    @Override
    public Optional<Supplier> findById(String id) {
        return supplierRepository.findById(id)
                .map(SupplierMapper::toDomain);
    }

    @Override
    public Optional<Supplier> findByPhone(String phone) {
        return supplierRepository.findByPhone(phone)
                .map(SupplierMapper::toDomain);
    }

    @Override
    public Optional<Supplier> findByEmail(String email) {
        return supplierRepository.findByEmail(email)
                .map(SupplierMapper::toDomain);
    }
}
