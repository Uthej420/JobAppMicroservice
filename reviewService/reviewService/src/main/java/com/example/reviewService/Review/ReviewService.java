package com.example.reviewService.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements ReviewRepo{    @Autowired
    private ReviewJpaRepo reviewJpaRepo;


    @Override
    public List<Review> getReviews(int companyId){

        List<Review> reviews = reviewJpaRepo.findByCompanyId(companyId);
        //System.out.println(reviews);
        return  reviews;
    }

    @Override
    public Review addReview(int companyId, Review review) {
        if(companyId != 0 && review != null){
            review.setCompanyId(companyId);
            reviewJpaRepo.save(review);
        }
        return reviewJpaRepo.save(review);
    }
    @Override
    public Review getReviewById(int reviewId){
        return reviewJpaRepo.findById(reviewId).orElse(null);
    }
    @Override
    public Review updateReview( int reviewId, Review review) {
        Review newReview = reviewJpaRepo.findById(reviewId).get();
        if (review != null){
            if (newReview.getTitle() != null) {
                newReview.setTitle(review.getTitle());
            }
            if (newReview.getDescription() != null) {
                newReview.setDescription(review.getDescription());
            }
            if (newReview.getCompanyId() != 0) {
                newReview.setCompanyId(review.getCompanyId());
            }
        }

        return reviewJpaRepo.save(newReview);

    }

    @Override
    public void deleteReview(int reviewId) {
            reviewJpaRepo.deleteById(reviewId);
    }


}
