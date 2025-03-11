package com.example.jobService.Job;

import java.util.List;

public interface JobRepo {
    List<Job> getJobs();
    Job getJobById(int jobId,int companyId);
    Job addJob(Job job);
    Job updateJob(int jobId,Job job);
    List<Job> deleteJob(int jobId);

}
