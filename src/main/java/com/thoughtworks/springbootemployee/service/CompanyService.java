package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

public class CompanyService {


    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return this.companyRepository.findAllCompanies();
    }

    public Company findCompanyByID(String companyID) {
        return this.companyRepository.findCompanyByID(companyID);
    }

    public List<Employee> findEmployeesByCompanyID(String companyID) {
        return this.companyRepository.findEmployeesByCompanyID(companyID);
    }
}