package com.learning.path.dto;

import lombok.Data;

@Data
public class ResourceDTO {
    private Long id;
    private String title;
    private String url;
    private String type;
    private Long topicId;
}