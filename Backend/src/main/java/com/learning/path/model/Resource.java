package com.learning.path.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resources")
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String type; // VIDEO, ARTICLE, QUIZ, etc.

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}