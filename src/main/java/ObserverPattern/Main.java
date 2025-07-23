package ObserverPattern;

public class Main {
    /*
    public interface Observer {
    void update(String message);
}

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(String message);
}

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements Subject {
    private final List<Observer> subscribers = new ArrayList<>();

    public void uploadVideo(String title) {
        System.out.println("Channel: Uploaded new video: " + title);
        notifyObservers("New video uploaded: " + title);
    }

    public void attach(Observer o) {
        subscribers.add(o);
    }

    public void detach(Observer o) {
        subscribers.remove(o);
    }

    public void notifyObservers(String message) {
        for (Observer o : subscribers) {
            o.update(message);
        }
    }
}

public class Subscriber implements Observer {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        Subscriber alice = new Subscriber("Alice");
        Subscriber bob = new Subscriber("Bob");

        channel.attach(alice);
        channel.attach(bob);

        channel.uploadVideo("Observer Pattern Explained!");
    }
}
     */
}
