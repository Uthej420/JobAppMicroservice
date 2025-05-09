package com.example.reviewService.Review;

import java.util.List;

public interface ReviewRepo {

    List<Review> getReviews(int companyId);

    Review getReviewById(int reviewId);
    boolean addReview(int companyId, Review review);
    Review updateReview(int reviewId, Review review);
    void deleteReview(int reviewId);
}
