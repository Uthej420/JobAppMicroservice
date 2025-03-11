package com.example.jobService.Job;

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
    public ResponseEntity<List<Job>> getJobs(){
        return ResponseEntity.ok(jobService.getJobs());
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable("jobId") int jobId,@RequestParam int companyId){
        return ResponseEntity.ok(jobService.getJobById(jobId,companyId ));
    }
    @PostMapping()
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        return new ResponseEntity<>(jobService.addJob(job), HttpStatus.CREATED);
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
