package com.learning.path.service;

import com.learning.path.model.Topic;
import java.util.List;
import java.util.Optional;

public interface TopicService {
    Topic createTopic(Topic topic);
    Optional<Topic> getTopicById(Long id);
    List<Topic> getAllTopics();
    List<Topic> getTopicsByLearningPathId(Long learningPathId);
    Topic updateTopic(Long id, Topic topic);
    void deleteTopic(Long id);
}