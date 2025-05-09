package com.example.companyService.Company;

import com.example.companyService.Company.Clients.ReviewClient;
import com.example.companyService.Company.dto.ReviewMsg;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService implements CompanyRepo{

    @Autowired
    private CompanyJpaRepo companyJpaRepo;
    @Autowired
    private ReviewClient reviewClient;

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

    @Override
    public void updateCompanyRating(ReviewMsg reviewMsg) {
        Company company = companyJpaRepo.findById(reviewMsg.getCompanyId()).
                orElseThrow(() -> new NotFoundException("Company Not Found" + reviewMsg.getCompanyId()));
        double averageRating = reviewClient.getAverageRatingForCompany(reviewMsg.getCompanyId());
        company.setRating(averageRating);
        companyJpaRepo.save(company);
    }

}
