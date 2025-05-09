package com.example.companyService.Company.messaging;

import com.example.companyService.Company.CompanyService;
import com.example.companyService.Company.dto.ReviewMsg;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMsgConsumer {
    private final CompanyService companyService;

    public ReviewMsgConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMsg(ReviewMsg reviewMsg){
        companyService.updateCompanyRating(reviewMsg);
    }
}
