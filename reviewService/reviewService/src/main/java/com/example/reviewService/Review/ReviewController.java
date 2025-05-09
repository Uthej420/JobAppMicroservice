package com.example.companyService.Company.reviewService.Review;

import com.example.companyService.Company.reviewService.Review.messaging.ReviewMsgProducer;
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
    @Autowired
    private ReviewMsgProducer reviewMsgProducer;

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam int companyId){
        return ResponseEntity.ok(reviewService.getReviews(companyId));
    }
    @GetMapping("/{reviewId}")
    public  ResponseEntity<Review> getReviewById(@PathVariable("reviewId") int reviewId){
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam int companyId, @RequestBody Review review){
         boolean isReivewSaved = reviewService.addReview(companyId,review);
                if(isReivewSaved){
                    reviewMsgProducer.sendMsg(review);
                    return new ResponseEntity("Review Added Successfully",HttpStatus.CREATED);
                }else {
                    return new ResponseEntity<>("Review Not Saved",HttpStatus.NOT_FOUND);
                }

    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable("reviewId")int reviewId,@RequestBody Review review){
        reviewMsgProducer.sendMsg(review);
        return new ResponseEntity<>(reviewService.updateReview(reviewId,review),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") int reviewId){
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.MOVED_PERMANENTLY);

    }

}
