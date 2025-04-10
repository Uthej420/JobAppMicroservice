package com.example.jobService.Job;

import com.example.jobService.Job.DTO.JobDTO;

import java.util.List;

public interface JobRepo {
    List<JobDTO> getJobs();
    JobDTO getJobById(int jobId);
    JobDTO addJob(int companyId,Job job);
    Job updateJob(int jobId,Job job);
    List<Job> deleteJob(int jobId);

}
