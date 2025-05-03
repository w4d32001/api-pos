package com.nocode.main.application.usecases;

import com.nocode.main.domain.model.Company;
import com.nocode.main.domain.model.dto.CompanyDto;
import com.nocode.main.domain.model.dto.request.company.CreateCompany;
import com.nocode.main.domain.model.dto.request.company.UpdateCompany;
import org.springframework.data.domain.Page;

public interface ICompanyService {
    Page<CompanyDto> findAllCompanies(String search, int page, int size);

    void saveCompany(CreateCompany request);

    void updateCompany(String id, UpdateCompany request);

    void deleteCompany(String id);
}
