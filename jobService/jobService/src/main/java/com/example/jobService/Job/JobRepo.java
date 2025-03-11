package com.example.jobService.Job;

import com.example.jobService.Job.DTO.JobDTO;

import java.util.List;

public interface JobRepo {
    List<JobDTO> getJobs();
    JobDTO getJobById(int jobId);
    Job addJob(Job job);
    Job updateJob(int jobId,Job job);
    List<Job> deleteJob(int jobId);

}
