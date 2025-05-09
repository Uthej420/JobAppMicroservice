package com.example.companyService.Company.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWSERVICE")
public interface ReviewClient {
    @GetMapping("/reviews/averageRating")
    double getAverageRatingForCompany(@RequestParam int companyId);
}
