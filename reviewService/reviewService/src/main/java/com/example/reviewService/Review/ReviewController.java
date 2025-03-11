package com.example.reviewService.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<List<Review>> getReviews(@RequestParam int companyId){
        return ResponseEntity.ok(reviewService.getReviews(companyId));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@RequestParam int companyId,@PathVariable("reviewId") int reviewId){
        return ResponseEntity.ok(reviewService.getReviewById(companyId, reviewId));
    }

    @PostMapping()
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        return new ResponseEntity(reviewService.addReview(review),HttpStatus.CREATED);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable("reviewId")int reviewId,@RequestBody Review review){
        return new ResponseEntity<>(reviewService.updateReview(reviewId,review),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") int reviewId){
        reviewService.deleteReview(reviewId);

    }

}
