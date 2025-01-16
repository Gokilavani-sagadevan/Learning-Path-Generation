package com.learning.path.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_analytics")
@Data
public class UserAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer totalCoursesCompleted;
    private Integer totalAssessmentsCompleted;
    private Double averageScore;
    private LocalDateTime lastActivityDate;

    // Standard getters and setters
}