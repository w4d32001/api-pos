package com.nocode.main.application.service;

import com.nocode.main.application.mapper.SupplierDtoMapper;
import com.nocode.main.application.usecases.ISupplierService;
import com.nocode.main.domain.model.Supplier;
import com.nocode.main.domain.model.dto.SupplierDto;
import com.nocode.main.domain.model.dto.request.supplier.CreateSupplier;
import com.nocode.main.domain.model.dto.request.supplier.UpdateSupplier;
import com.nocode.main.domain.port.ISupplierPersistencePort;
import com.nocode.main.infrastructure.shared.exception.ConflictException;
import com.nocode.main.infrastructure.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SupplierService implements ISupplierService {

    private final ISupplierPersistencePort supplierPersistencePort;

    @Autowired
    public SupplierService(ISupplierPersistencePort supplierPersistencePort) {
        this.supplierPersistencePort = supplierPersistencePort;
    }

    @Override
    public void saveSupplier(CreateSupplier request) {
        supplierPersistencePort.findByPhone(request.getPhone())
                .ifPresent(n -> {
                    throw new ConflictException("El proveedor con el teléfono: '" + request.getPhone() + "' ya existe.");
                });

        supplierPersistencePort.findByDocumentNumber(request.getDocumentNumber())
                .ifPresent(n -> {
                    throw new ConflictException("El proveedor con el número de documento: '" + request.getDocumentNumber() + "' ya existe.");
                });

        supplierPersistencePort.findByEmail(request.getEmail())
                .ifPresent(n -> {
                    throw new ConflictException("El proveedor con el email: '" + request.getEmail() + "' ya existe.");
                });

        Supplier supplier = Supplier.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .phone(request.getPhone())
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        supplierPersistencePort.saveSupplier(supplier);

    }

    @Override
    public Page<SupplierDto> findAllSuppliers(String search, int page, int size) {
        Page<Supplier> suppliers = supplierPersistencePort.findAllSuppliers(search, page, size);

        return suppliers
                .map(SupplierDtoMapper::toDto);

    }

    @Override
    public void updateSupplier(String id, UpdateSupplier request) {
        Supplier supplier = supplierPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));

        supplierPersistencePort.findByPhone(request.getPhone())
                .filter(d -> !d.getId().equals(id))
                .ifPresent(n -> {
                    throw new ConflictException("El proveedor con el teléfono: '" + request.getPhone() + "' ya existe.");
                });

        supplierPersistencePort.findByDocumentNumber(request.getDocumentNumber())
                .filter(d -> !d.getId().equals(id))
                .ifPresent(n -> {
                    throw new ConflictException("El proveedor con el número de documento: '" + request.getDocumentNumber() + "' ya existe.");
                });

        supplierPersistencePort.findByEmail(request.getEmail())
                .filter(d -> !d.getId().equals(id))
                .ifPresent(n -> {
                    throw new ConflictException("El proveedor con el email: '" + request.getEmail() + "' ya existe.");
                });


        supplier.setName(supplier.getName());
        supplier.setDocumentType(supplier.getDocumentType());
        supplier.setDocumentNumber(supplier.getDocumentNumber());
        supplier.setPhone(supplier.getPhone());
        supplier.setEmail(supplier.getEmail());
        supplier.setUpdatedAt(LocalDateTime.now());

        supplierPersistencePort.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(String id) {
        Supplier supplier = supplierPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));

        supplierPersistencePort.deleteSupplier(supplier.getId());

    }

    @Override
    public SupplierDto findByDocumentNumber(String documentNumber) {
        Supplier supplier = supplierPersistencePort.findByDocumentNumber(documentNumber).orElseThrow(
                () -> new ResourceNotFoundException("Supplier", "documentNumber", documentNumber)
        );
        return SupplierDtoMapper.toDto(supplier);
    }

    @Override
    public SupplierDto findById(String id) {
        Supplier supplier = supplierPersistencePort.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Supplier", "id", id)
        );
        return SupplierDtoMapper.toDto(supplier);
    }

    @Override
    public SupplierDto findByPhone(String phone) {
        Supplier supplier = supplierPersistencePort.findByPhone(phone).orElseThrow(
                () -> new ResourceNotFoundException("Supplier", "phone", phone)
        );
        return SupplierDtoMapper.toDto(supplier);
    }

    @Override
    public SupplierDto findByEmail(String email) {
        Supplier supplier = supplierPersistencePort.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Supplier", "email", email)
        );
        return SupplierDtoMapper.toDto(supplier);
    }
}
