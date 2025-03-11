package com.example.jobService.Job.mapper;

import com.example.jobService.Job.DTO.JobDTO;
import com.example.jobService.Job.Job;
import com.example.jobService.Job.external.Company;
import com.example.jobService.Job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobCompanyDto(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(job.getJobId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMin_sal(job.getMin_sal());
        jobDTO.setMax_sal(job.getMax_sal());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
