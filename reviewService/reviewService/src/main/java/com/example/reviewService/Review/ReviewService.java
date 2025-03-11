package com.example.reviewService.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService implements ReviewRepo{    @Autowired
    private ReviewJpaRepo reviewJpaRepo;


    @Override
    public List<Review> getReviews(int companyId){
        List<Review> reviews = reviewJpaRepo.findAll();
        //System.out.println(reviews);
        return  reviews;
    }

    @Override
    public Review addReview(Review review) {
        return reviewJpaRepo.save(review);
    }
    @Override
    public Review getReviewById(int companyId, int reviewId){
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
        try {
            reviewJpaRepo.deleteById(reviewId);
            throw new ResponseStatusException(HttpStatus.MOVED_PERMANENTLY);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }


}
