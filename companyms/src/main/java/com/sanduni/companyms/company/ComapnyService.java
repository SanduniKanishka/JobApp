package com.sanduni.companyms.company;

import com.sanduni.companyms.dto.ReviewMessage;

import java.util.List;

public interface ComapnyService{

    List<Company> findAll();

    void createCompany(Company company);

    Company getById(Long id);

    boolean deleteById(Long id);

    boolean updateCompany(Long id, Company company);

    void updateComapnyRating(ReviewMessage reviewMessage);
}
