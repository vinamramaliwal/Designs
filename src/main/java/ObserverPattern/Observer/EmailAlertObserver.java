package ObserverPattern.Observer;

import ObserverPattern.Observable.StocksObservable;

public class EmailAlertObserver implements  NotificationObserver{
    StocksObservable obj;
    public EmailAlertObserver(StocksObservable obj){
        this.obj=obj;
    }
    @Override
    public void update() {
        System.out.println("Email sent::New stock of iphone " + obj.getData());
    }
}
