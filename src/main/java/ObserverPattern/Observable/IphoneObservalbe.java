package ObserverPattern.Observable;

import ObserverPattern.Observer.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservalbe implements  StocksObservable{

    List<NotificationObserver> notificationObserverList =  new ArrayList<>();
    int currentStock =0;

    @Override
    public void add(NotificationObserver obj) {
        notificationObserverList.add(obj);
    }

    @Override
    public void remove(NotificationObserver obj) {
        notificationObserverList.remove(obj);
    }

    @Override
    public void setData(int data) {
        if(currentStock==0) {
            currentStock= currentStock + data;
            notifyObserver();
        }


    }

    @Override
    public void notifyObserver() {
        for(NotificationObserver notificationObserver: notificationObserverList){
            notificationObserver.update();
        }
    }

    @Override
    public int getData() {
        return currentStock;
    }
}
