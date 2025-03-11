package com.example.companyService.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService implements CompanyRepo{

    @Autowired
    private CompanyJpaRepo companyJpaRepo;

    @Override
    public List<Company> getCompanies() {
        return companyJpaRepo.findAll();
    }

    @Override
    public Company getCompanyById(int companyId) {
        return companyJpaRepo.findById(companyId).get();
    }

    @Override
    public Company addCompany(Company company) {

        return companyJpaRepo.save(company);
    }

    @Override
    public Company updateCompany(int companyId, Company company) {
        Company newCompany = companyJpaRepo.findById(companyId).get();
        if(company.getName()!= null){
            newCompany.setName(company.getName());
        }
        if(company.getDescription()!= null){
            newCompany.setDescription(company.getDescription());
        }
        return  companyJpaRepo.save(newCompany);
    }

    @Override
    public List<Company> deleteCompany(int companyId) {
        companyJpaRepo.deleteById(companyId);
        return companyJpaRepo.findAll();
    }

}
