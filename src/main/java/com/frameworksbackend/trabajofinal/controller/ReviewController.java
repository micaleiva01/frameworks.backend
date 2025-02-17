package com.frameworksbackend.trabajofinal.controller;

import com.frameworksbackend.trabajofinal.dto.NewReviewDTO;
import com.frameworksbackend.trabajofinal.model.Review;
import com.frameworksbackend.trabajofinal.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody NewReviewDTO reviewRequest) {
        Review review = reviewService.createReview(
                reviewRequest.getMovieId(),
                reviewRequest.getReviewerName(),
                reviewRequest.getComment()
        );
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovie(movieId));
    }
}
