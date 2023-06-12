package QueueLLD.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {
    private String name;
    private String id;
    private List<Message> messages;
    private List<TopicSubscriber> subscribers;

    public  Topic(String name, String topicId){
        this.name = name;
        this.id = topicId;
        messages =  new ArrayList<>();
        subscribers =  new ArrayList<>();
    }

    public synchronized void addMesssage(Message message){
        messages.add(message);
    }

    public void addSubscriber(TopicSubscriber subscriber){
        subscribers.add(subscriber);
    }

}
