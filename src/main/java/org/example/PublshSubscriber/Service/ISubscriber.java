package org.example.PublshSubscriber.Service;

import org.example.PublshSubscriber.model.Message;

public interface ISubscriber {
    String getId();
    void onMessage(Message message) throws InterruptedException;
}