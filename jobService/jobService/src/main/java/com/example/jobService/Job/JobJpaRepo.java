package com.example.jobService.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobJpaRepo extends JpaRepository<Job,Integer> {
}
