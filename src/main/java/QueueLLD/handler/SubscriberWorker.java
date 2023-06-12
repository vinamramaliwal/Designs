package QueueLLD.handler;

import QueueLLD.model.Message;
import QueueLLD.model.Topic;
import QueueLLD.model.TopicSubscriber;
import lombok.SneakyThrows;

public class SubscriberWorker implements Runnable{

    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
            synchronized (topicSubscriber){
                do{
                    int currentOffset= topicSubscriber.getOffset().get();
                    while(currentOffset>= topic.getMessages().size()){
                        topicSubscriber.wait();
                    }
                    Message message = topic.getMessages().get(currentOffset);
                    topicSubscriber.getSubscriber().consume(message);
                    topicSubscriber.getOffset().compareAndSet(currentOffset, currentOffset + 1);
                } while (true);
            }
    }

    public void wakeUpIfNeeded() {
        synchronized (topicSubscriber){
            topicSubscriber.notify();
        }
    }
}
