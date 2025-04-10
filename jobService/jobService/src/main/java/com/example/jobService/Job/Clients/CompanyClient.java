package com.example.jobService.Job.Clients;

import com.example.jobService.Job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "COMPANYSERVICE")
public interface CompanyClient {
    @GetMapping("/companies/{companyId}")
    Company getCompany(@PathVariable("companyId") int companyId);
}
