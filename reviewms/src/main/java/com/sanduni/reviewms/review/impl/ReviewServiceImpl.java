package com.sanduni.reviewms.review.impl;

import com.sanduni.reviewms.review.Review;
import com.sanduni.reviewms.review.ReviewRepository;
import com.sanduni.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
//        Optional<Review> reviewOptional = reviewRepository.findById(id);
        Review review1 = reviewRepository.findById(reviewId).orElse(null);
        if (review1 != null){
            review1.setCompanyId(review.getCompanyId());
            review1.setDescription(review.getDescription());
            review1.setRating(review.getRating());
            review1.setDescription(review.getDescription());
            reviewRepository.save(review1);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null){
            reviewRepository.delete(review);
            return true;
        }else {
            return false;
        }
    }

//    @Override
//    public boolean deleteReview(Long companyId, Long reviewId) {
//        if (companyService.getById(companyId) != null
//            && reviewRepository.existsById(reviewId)){
//
//            Review review = reviewRepository.findById(reviewId).orElse(null);
//            Company company = review.getCompany();
//            company.getReviews().remove(review);
//            companyService.updateCompany(companyId, company);
//            reviewRepository.deleteById(reviewId);
//            return true;
//        }else {
//            return false;
//        }
//    }
}
