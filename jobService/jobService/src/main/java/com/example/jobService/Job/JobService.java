package com.example.jobService.Job;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements JobRepo {

    @Autowired
    private JobJpaRepo jobJpaRepo;
    @Override
    public List<Job> getJobs() {
        List<Job> jobs =  jobJpaRepo.findAll();
        return jobs;
    }

    @Override
    public Job getJobById(int jobId, int companyId) {
        return jobJpaRepo.findById(jobId).get();
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
