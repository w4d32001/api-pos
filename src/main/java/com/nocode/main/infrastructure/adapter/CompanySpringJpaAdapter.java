package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.Company;
import com.nocode.main.domain.port.ICompanyPersistencePort;
import com.nocode.main.infrastructure.adapter.entity.CompanyEntity;
import com.nocode.main.infrastructure.adapter.mapper.CompanyMapper;
import com.nocode.main.infrastructure.adapter.repository.ICompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CompanySpringJpaAdapter implements ICompanyPersistencePort {

    private final ICompanyRepository companyRepository;

    public CompanySpringJpaAdapter(final ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<Company> findAllCompanies(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompanyEntity> companyEntities = companyRepository.findByNameContainingIgnoreCase(search, pageable);

        return companyEntities
                .map(CompanyMapper::toDomain);
    }

    @Override
    public void saveCompany(Company company) {
        CompanyEntity entity = CompanyMapper.toEntity(company);

        companyRepository.save(entity);
    }

    @Override
    public void updateCompany(Company company) {
        CompanyEntity entity = CompanyMapper.toEntity(company);

        companyRepository.save(entity);
    }

    @Override
    public void deleteCompany(String id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findById(String id) {
        return companyRepository.findById(id)
                .map(CompanyMapper::toDomain);
    }
}
