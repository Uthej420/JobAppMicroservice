package com.example.jobService.Job.DTO;

import com.example.jobService.Job.external.Company;
import com.example.jobService.Job.external.Review;

import java.util.ArrayList;
import java.util.List;

public class JobDTO {
    private int jobId;
    private String title ;
    private String description;
    private int min_sal;
    private int max_sal;
    private Company company;
    private List<Review> reviews;

    public JobDTO(){}

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMin_sal() {
        return min_sal;
    }

    public void setMin_sal(int min_sal) {
        this.min_sal = min_sal;
    }

    public int getMax_sal() {
        return max_sal;
    }

    public void setMax_sal(int max_sal) {
        this.max_sal = max_sal;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
