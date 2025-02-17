package com.frameworksbackend.trabajofinal.service;

import com.frameworksbackend.trabajofinal.model.Review;
import java.util.List;

public interface IReviewService {
    Review createReview(Long movieId, String reviewerName, String comment);
    List<Review> getAllReviews();
    List<Review> getReviewsByMovie(Long movieId);
}
