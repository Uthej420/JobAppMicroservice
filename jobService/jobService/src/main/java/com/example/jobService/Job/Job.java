package com.example.jobService.Job;


import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String title ;
    private String description;
    private int min_sal;
    private int max_sal;
    private int companyId;

    public Job(){}

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

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
}
