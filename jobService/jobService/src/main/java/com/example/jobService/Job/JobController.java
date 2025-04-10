package com.example.jobService.Job;

import com.example.jobService.Job.DTO.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired JobService jobService;

    @GetMapping()
    public ResponseEntity<List<JobDTO>> getJobs(){
        return ResponseEntity.ok(jobService.getJobs());
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable("jobId") int jobId){
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }
    @PostMapping()
    public ResponseEntity<JobDTO> addJob(@RequestParam int companyId,@RequestBody Job job){
        return new ResponseEntity<>(jobService.addJob(companyId,job), HttpStatus.CREATED);
    }
    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable("jobId") int jobId,@RequestBody Job job){
        return  new ResponseEntity<>(jobService.updateJob(jobId,job),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{jobId}")
    public ResponseEntity<List<Job>> deleteJob(@PathVariable("jobId")int jobId){
        return new ResponseEntity<>(jobService.deleteJob(jobId),HttpStatus.MOVED_PERMANENTLY);
    }

}
