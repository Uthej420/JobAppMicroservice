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

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam int companyId){
        return ResponseEntity.ok(reviewService.getReviews(companyId));
    }
    @GetMapping("/{reviewId}")
    public  ResponseEntity<Review> getReviewById(@PathVariable("reviewId") int reviewId){
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestParam int companyId, @RequestBody Review review){
        return new ResponseEntity(reviewService.addReview(companyId,review),HttpStatus.CREATED);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable("reviewId")int reviewId,@RequestBody Review review){
        return new ResponseEntity<>(reviewService.updateReview(reviewId,review),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") int reviewId){
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.MOVED_PERMANENTLY);

    }

}
