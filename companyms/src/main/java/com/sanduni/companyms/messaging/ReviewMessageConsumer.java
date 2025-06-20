package com.sanduni.companyms.messaging;

import com.sanduni.companyms.company.ComapnyService;
import com.sanduni.companyms.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final ComapnyService comapnyService;

    public ReviewMessageConsumer(ComapnyService comapnyService){
        this.comapnyService  = comapnyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        comapnyService.updateComapnyRating(reviewMessage);
    }
}
