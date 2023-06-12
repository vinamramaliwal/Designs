package QueueLLD.model;

import QueueLLD.publicInterface.ISubscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class TopicSubscriber {
    ISubscriber subscriber;
    AtomicInteger offset;
    public TopicSubscriber(ISubscriber subscriber){
        this.subscriber = subscriber;
        offset = new AtomicInteger(0);
    }
}
