package ObserverPattern.Observer;

import ObserverPattern.Observable.StocksObservable;

public class MessageAlertObserver implements NotificationObserver{
    StocksObservable obj;
    public MessageAlertObserver(StocksObservable obj){
        this.obj=obj;
    }
    @Override
    public void update() {
        System.out.println("Message sent::New stock of iphone " + obj.getData());
    }
}
