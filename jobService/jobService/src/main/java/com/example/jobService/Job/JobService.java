package com.example.jobService.Job;


import com.example.jobService.Job.Clients.CompanyClient;
import com.example.jobService.Job.Clients.ReviewClient;
import com.example.jobService.Job.DTO.JobDTO;
import com.example.jobService.Job.external.Company;
import com.example.jobService.Job.external.Review;
import com.example.jobService.Job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService implements JobRepo {
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;

    @Autowired
    private JobJpaRepo jobJpaRepo;

    int attempt = 0;
    @Override
//    @CircuitBreaker(name = "companyBreaker", fallbackMethod="companyBreakerFallback")
//    @Retry(name = "companyBreaker",
//            fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker",
          fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> getJobs() {
        System.out.println(attempt);
        List<Job> jobs =  jobJpaRepo.findAll();
        //List<JobDTO> jobDTOS = new ArrayList<>();
        //RestTemplate restTemplate = new RestTemplate();
        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());

    }
    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("dummy");
        return list;
    }
    private JobDTO convertToDto(Job job){
//        Using RestTemplate getting company object data
//        Company company = restTemplate.getForObject(
//                "http://COMPANYSERVICE:8082/companies/" + job.getCompanyId(),
//                Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());
//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                "http://REVIEWSERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        System.out.println(reviews.size());
        JobDTO jobDTO = JobMapper.mapToJobCompanyDto(job,company,reviews);
        return jobDTO;
    }

    @Override
    public JobDTO getJobById(int jobId) {
        Job job = jobJpaRepo.findById(jobId).orElse(null);
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        JobDTO jobDTO = JobMapper.mapToJobCompanyDto(job, company,reviews);
        return jobDTO;
    }

    @Override
    public JobDTO addJob(int companyId,Job job) {
        job.setCompanyId(companyId);
        jobJpaRepo.save(job);
        return  convertToDto(job);
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
