package com.example.reviewService.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewJpaRepo extends JpaRepository<Review,Integer> {
    @Query("SELECT r FROM Review r where r.companyId = :companyId ")
    List<Review> findByCompanyId(@Param("companyId") int companyId);
}
