package org.example.PublshSubscriber.Service;

import org.example.PublshSubscriber.model.Message;

public interface IPublisher {
    String getId();
    void publish(String topicId, Message message) throws IllegalArgumentException;
}