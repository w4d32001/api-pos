package com.nocode.main.business;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nocode.main.dtos.CompanyDto;
import com.nocode.main.entities.CompanyEntity;
import com.nocode.main.repositories.ICompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyBusiness {
      
      @Autowired
      private ICompanyRepository _repo;

      public Page<CompanyDto> findAll(String search, int page, int size) {

            Pageable pageable = PageRequest.of(page, size);

            Page<CompanyEntity> companyEntityPage = _repo.findByNameContainingIgnoreCase(search, pageable);

            return companyEntityPage.map(
                  company -> {
                        CompanyDto dto = new CompanyDto();
                        dto.setId(company.getId());
                        dto.setLogo(company.getLogo());
                        dto.setName(company.getName());
                        dto.setAddress(company.getAddress());
                        dto.setPhone(company.getPhone());
                        dto.setRuc(company.getRuc());
                        dto.setCreatedAt(company.getCreatedAt());
                        dto.setUpdatedAt(company.getUpdatedAt());
                        return  dto;
                  }
            );

      }

      @Transactional 
      public void createCompany(CompanyDto dto){
          

            CompanyEntity entity = CompanyEntity.builder()
                  .id(UUID.randomUUID().toString())
                  .logo(dto.getLogo())
                  .name(dto.getName())
                  .address(dto.getAddress())
                  .phone(dto.getPhone())
                  .ruc(dto.getRuc())
                  .createdAt(LocalDateTime.now())
                  .updatedAt(LocalDateTime.now())
                  .build();
            _repo.save(entity);
      }

      @Transactional
      public void updateCompany(String id, CompanyDto dto){

            CompanyEntity entity = _repo.findById(id)
                  .orElseThrow(() -> new RuntimeException("No se encontró la empresa con id: " + id));

            entity.setLogo(dto.getLogo());
            entity.setName(dto.getName());
            entity.setAddress(dto.getAddress());
            entity.setPhone(dto.getPhone());
            entity.setRuc(dto.getRuc());
            entity.setUpdatedAt(LocalDateTime.now());

            _repo.save(entity);
      }

      @Transactional
      public void deleteCompany(String id) {

            CompanyEntity entity = _repo.findById(id)
                  .orElseThrow(() -> new RuntimeException("No se encontró la empresa con id: " + id));

            _repo.delete(entity);
      }

}
