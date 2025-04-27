package com.nocode.main.business;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nocode.main.dtos.SupplierDto;
import com.nocode.main.entities.SupplierEntity;
import com.nocode.main.exception.ConflictException;
import com.nocode.main.repositories.ISupplierRepository;

import jakarta.transaction.Transactional;

@Service
public class SupplierBusiness {

      @Autowired
      private ISupplierRepository _repo;

      public Page<SupplierDto> findAllSuppliers(String search, int page, int size) {

            Pageable pageable = PageRequest.of(page, size);
            Page<SupplierEntity> supplierEntityPage = _repo.findByNameContainingIgnoreCase(search, pageable);

            return supplierEntityPage.map(
                        supplier -> {
                              SupplierDto dto = new SupplierDto();
                              dto.setId(supplier.getId());
                              dto.setName(supplier.getName());
                              dto.setDocumentType(supplier.getDocumentType());
                              dto.setDocumentNumber(supplier.getDocumentNumber());
                              dto.setPhone(supplier.getPhone());
                              dto.setEmail(supplier.getEmail());
                              dto.setCreatedAt(supplier.getCreatedAt());
                              dto.setUpdatedAt(supplier.getUpdatedAt());
                              return dto;
                        });

      }

      @Transactional
      public void createSupplier(SupplierDto dto) {

            _repo.findByPhone(dto.getPhone())
                  .ifPresent(n -> {
                        throw new ConflictException("El proveedor con el teléfono: '" + dto.getPhone() + "' ya existe.");
                  });

            _repo.findByDocumentNumber(dto.getDocumentNumber())
                  .ifPresent(n -> {
                        throw new ConflictException("El proveedor con el número de documento: '" + dto.getDocumentNumber() + "' ya existe.");
                  });

            _repo.findByEmail(dto.getEmail())
                  .ifPresent(n -> {
                        throw new ConflictException("El proveedor con el email: '" + dto.getEmail() + "' ya existe.");
                  });

            SupplierEntity entity = SupplierEntity.builder()
                  .id(UUID.randomUUID().toString())
                  .name(dto.getName())
                  .documentType(dto.getDocumentType())
                  .documentNumber(dto.getDocumentNumber())
                  .phone(dto.getPhone())
                  .email(dto.getEmail())
                  .createdAt(LocalDateTime.now())
                  .updatedAt(LocalDateTime.now())
                  .build();
            _repo.save(entity);
      }

      @Transactional
      public void updateSupplier(String id, SupplierDto dto) {

            SupplierEntity entity = _repo.findById(id)
                  .orElseThrow(() -> new ConflictException("El proveedor con el id: '" + id + "' no existe."));

            entity.setName(dto.getName());
            entity.setDocumentType(dto.getDocumentType());
            entity.setDocumentNumber(dto.getDocumentNumber());
            entity.setPhone(dto.getPhone());
            entity.setEmail(dto.getEmail());
            entity.setUpdatedAt(LocalDateTime.now());

            _repo.save(entity);
      }

      @Transactional
      public void deleteSupplier(String id) {

            SupplierEntity entity = _repo.findById(id)
                  .orElseThrow(() -> new ConflictException("El proveedor con el id: '" + id + "' no existe."));

            _repo.delete(entity);
      }

}
