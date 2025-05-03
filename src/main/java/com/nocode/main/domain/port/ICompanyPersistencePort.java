package com.nocode.main.domain.port;

import com.nocode.main.domain.model.Company;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ICompanyPersistencePort {
    Page<Company> findAllCompanies(String search, int page, int size);

    void saveCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(String id);

    Optional<Company> findById(String id);
}
