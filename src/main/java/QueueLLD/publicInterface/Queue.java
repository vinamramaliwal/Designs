package QueueLLD.publicInterface;

import QueueLLD.handler.TopicHandler;
import QueueLLD.model.Message;
import QueueLLD.model.Topic;
import QueueLLD.model.TopicSubscriber;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Queue {
    private Map<String, TopicHandler> topichandlers;

    public Queue(){
        this.topichandlers = new HashMap<>();
    }

    public Topic createTopic(@NonNull final String topicName){
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topichandlers.put(topic.getId(),topicHandler);
        System.out.println("Craeted topic:" + topicName);
        return topic;
    }

    public void subscribe(ISubscriber subscriber, Topic topic){
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " Subscribed to topic " +  topic.getName());
    }

    public void publish(Topic topic, Message message){
        topic.addMesssage(message);
        new Thread(()-> topichandlers.get(topic.getId()).publish()).start();
    }

    public void resetOffset(@NonNull final Topic topic, @NonNull final ISubscriber subscriber, @NonNull final Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(() -> topichandlers.get(topic.getId()).startSubscriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }
}
