package com.example.companyService.Company;

import java.util.List;

public interface CompanyRepo {
    List<Company> getCompanies();
    Company getCompanyById(int companyId);
    Company addCompany(Company company);
    Company updateCompany(int companyId, Company company);
    List<Company> deleteCompany(int companyId);

}
