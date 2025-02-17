package com.frameworksbackend.trabajofinal.dto;

public class NewReviewDTO {

    private Long movieId;
    private String reviewerName;
    private String comment;

    public Long getMovieId() {
        return movieId;
    }
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
    public String getReviewerName() {
        return reviewerName;
    }
    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
