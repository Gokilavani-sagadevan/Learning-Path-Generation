package com.learning.path.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class RatingDTO {
    private Long id;
    private Long userId;
    private Long learningPathId;

    @Min(1)
    @Max(5)
    private int rating;

    @Size(max = 500)
    private String review;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getLearningPathId() { return learningPathId; }
    public void setLearningPathId(Long learningPathId) { this.learningPathId = learningPathId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}