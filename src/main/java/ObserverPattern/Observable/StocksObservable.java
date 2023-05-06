package ObserverPattern.Observable;

import ObserverPattern.Observer.NotificationObserver;

public interface StocksObservable {

    void add(NotificationObserver obj);
    void remove(NotificationObserver obj);
    void setData(int data);
    void notifyObserver();
    int getData();
}
