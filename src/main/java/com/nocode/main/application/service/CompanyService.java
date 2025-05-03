package com.nocode.main.application.service;

import com.nocode.main.application.mapper.CompanyDtoMapper;
import com.nocode.main.application.usecases.ICompanyService;
import com.nocode.main.domain.model.Company;
import com.nocode.main.domain.model.Supplier;
import com.nocode.main.domain.model.dto.CompanyDto;
import com.nocode.main.domain.model.dto.request.company.CreateCompany;
import com.nocode.main.domain.model.dto.request.company.UpdateCompany;
import com.nocode.main.domain.port.ICompanyPersistencePort;
import com.nocode.main.infrastructure.adapter.externalservices.CloudinaryService;
import com.nocode.main.infrastructure.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CompanyService implements ICompanyService {

    private final ICompanyPersistencePort companyPersistencePort;
    private final CloudinaryService cloudinaryService;

    public CompanyService(final ICompanyPersistencePort companyPersistencePort, final CloudinaryService cloudinaryService) {
        this.companyPersistencePort = companyPersistencePort;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Page<CompanyDto> findAllCompanies(String search, int page, int size) {
        Page<Company> companies = companyPersistencePort.findAllCompanies(search, page, size);
        return companies
                .map(CompanyDtoMapper::toDto);
    }

    @Override
    public void saveCompany(CreateCompany request) {

        String logo = cloudinaryService.uploadImage(request.getLogo());

        Company company = Company.builder()
                .id(UUID.randomUUID().toString())
                .logo(logo)
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .ruc(request.getRuc())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        companyPersistencePort.saveCompany(company);

    }

    @Override
    public void updateCompany(String id, UpdateCompany request) {
        Company company = companyPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));

        if (request.getLogo() != null && !request.getLogo().isEmpty()) {
            String logo = cloudinaryService.uploadImage(request.getLogo());
            company.setLogo(logo);
        }

        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setPhone(request.getPhone());
        company.setRuc(request.getRuc());
        company.setUpdatedAt(LocalDateTime.now());

        companyPersistencePort.updateCompany(company);

    }

    @Override
    public void deleteCompany(String id) {
        Company company = companyPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));

        companyPersistencePort.deleteCompany(company.getId());
    }
}
