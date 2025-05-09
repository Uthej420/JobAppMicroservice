package com.example.reviewService.Review.messaging;

import com.example.reviewService.Review.Review;
import com.example.reviewService.Review.dto.ReviewMsg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMsgProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMsg(Review review){
        ReviewMsg reviewMsg = new ReviewMsg();
        reviewMsg.setReviewId(review.getReviewId());
        reviewMsg.setTitle(review.getTitle());
        reviewMsg.setDescription(review.getDescription());
        reviewMsg.setCompanyId(review.getCompanyId());
        reviewMsg.setRating(review.getRating());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMsg);
    }
}
