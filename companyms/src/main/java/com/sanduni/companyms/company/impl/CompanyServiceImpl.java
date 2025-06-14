package com.sanduni.companyms.company.impl;

import com.sanduni.companyms.company.ComapnyService;
import com.sanduni.companyms.company.Company;
import com.sanduni.companyms.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ComapnyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setDescription(updateCompany.getDescription());
            company.setName(updateCompany.getName());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
