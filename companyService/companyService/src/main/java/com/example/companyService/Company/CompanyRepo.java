package com.example.companyService.Company;

import com.example.companyService.Company.dto.ReviewMsg;

import java.util.List;

public interface CompanyRepo {
    List<Company> getCompanies();
    Company getCompanyById(int companyId);
    Company addCompany(Company company);
    Company updateCompany(int companyId, Company company);
    List<Company> deleteCompany(int companyId);
    public void updateCompanyRating(ReviewMsg reviewMsg);
}
