package com.example.jobService.Job;


import com.example.jobService.Job.DTO.JobDTO;
import com.example.jobService.Job.external.Company;
import com.example.jobService.Job.external.Review;
import com.example.jobService.Job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService implements JobRepo {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private JobJpaRepo jobJpaRepo;
    @Override
    public List<JobDTO> getJobs() {
        List<Job> jobs =  jobJpaRepo.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        //RestTemplate restTemplate = new RestTemplate();
        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());

    }
    private JobDTO convertToDto(Job job){

        Company company = restTemplate.getForObject(
                "http://COMPANYSERVICE:8082/companies/" + job.getCompanyId(),
                Company.class);

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://REVIEWSERVICE:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviews = reviewResponse.getBody();
        JobDTO jobDTO = JobMapper.mapToJobCompanyDto(job,company,reviews);
        //jobDTO.setCompany(company);
        return jobDTO;
    }

    @Override
    public JobDTO getJobById(int jobId) {
        Job job = jobJpaRepo.findById(jobId).get();
        return convertToDto(job);
    }

    @Override
    public Job addJob(Job job) {
        return jobJpaRepo.save(job);
    }

    @Override
    public Job updateJob(int jobId, Job job) {
        Job newJob = jobJpaRepo.findById(jobId).get();

        if(job.getTitle()!=null){
            newJob.setTitle(job.getTitle());
        }
        if(job.getDescription()!=null){
            newJob.setDescription(job.getDescription());
        }
        if(job.getMin_sal()!=0){
            newJob.setMin_sal(job.getMin_sal());
        }
        if(job.getMax_sal()!=0){
            newJob.setMax_sal(job.getMax_sal());
        }
        if(job.getCompanyId() != 0){
            newJob.setCompanyId(job.getCompanyId());
        }
        return jobJpaRepo.save(newJob);

    }

    @Override
    public List<Job> deleteJob(int jobId) {
        jobJpaRepo.deleteById(jobId);
        return jobJpaRepo.findAll();
    }


}
