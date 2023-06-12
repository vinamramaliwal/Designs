package QueueLLD.handler;

import QueueLLD.model.Topic;
import QueueLLD.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkers;


    public TopicHandler(Topic topic) {
        this.topic = topic;
        this.subscriberWorkers = new HashMap<>();
    }

    public void publish(){
        for(TopicSubscriber topicSubscriber: topic.getSubscribers()){
            startSubscriberWorker(topicSubscriber);
        }
    }

    public void startSubscriberWorker(TopicSubscriber topicSubscriber){
              String subscriberId = topicSubscriber.getSubscriber().getId();
              if(!subscriberWorkers.containsKey(subscriberId)){
                  SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
                  subscriberWorkers.put(subscriberId, subscriberWorker);
                  new Thread(subscriberWorker).start();
              }
        SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}
