package com.sanduni.reviewms.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews();

    boolean createReview(Long CompanyId, Review review);

    Review getReviewById(Long reviewId);

    List<Review> getAllReviews(Long companyId);

    boolean updateReview(Long reviewId, Review review);

    boolean deleteReviewById(Long id);

}
