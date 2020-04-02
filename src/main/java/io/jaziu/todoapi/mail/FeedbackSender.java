package io.jaziu.todoapi.mail;

public interface FeedbackSender {
    void sendFeedback(String from, String name, String feedback);
}
