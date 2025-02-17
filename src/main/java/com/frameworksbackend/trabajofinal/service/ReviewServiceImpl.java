package com.frameworksbackend.trabajofinal.service;

import com.frameworksbackend.trabajofinal.model.Review;
import com.frameworksbackend.trabajofinal.model.Movie;
import com.frameworksbackend.trabajofinal.repository.ReviewRepository;
import com.frameworksbackend.trabajofinal.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Review createReview(Long movieId, String reviewerName, String comment) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + movieId));

        Review review = new Review();
        review.setMovie(movie);
        review.setReviewerName(reviewerName);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
}