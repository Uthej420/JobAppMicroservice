package com.example.companyService.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJpaRepo extends JpaRepository<Company,Integer> {
}
