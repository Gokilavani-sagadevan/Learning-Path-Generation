package com.learning.path.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.learning.path.service.SearchService;
import com.learning.path.service.impl.SearchServiceImpl;
import com.learning.path.specification.SearchSpecificationBuilder;

@Configuration
@EnableJpaRepositories(basePackages = "com.learning.path.repository")
public class SearchConfig {

    @Bean
    public SearchService searchService() {
        return new SearchServiceImpl();
    }

    @Bean
    public SearchSpecificationBuilder searchSpecificationBuilder() {
        return new SearchSpecificationBuilder();
    }
}