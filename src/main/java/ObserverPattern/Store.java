package ObserverPattern;

import ObserverPattern.Observable.IphoneObservalbe;
import ObserverPattern.Observable.StocksObservable;
import ObserverPattern.Observer.EmailAlertObserver;
import ObserverPattern.Observer.MessageAlertObserver;
import ObserverPattern.Observer.NotificationObserver;

public class Store {
   // It defines a one-to-many dependency between objects so that when one object (Subject) changes state, all its dependents (Observers)
   // are notified automatically.
            public static void main(String args[]){
                StocksObservable iphone= new IphoneObservalbe();
                NotificationObserver observer1 = new EmailAlertObserver(iphone);
                NotificationObserver observer2 = new EmailAlertObserver(iphone);
                NotificationObserver observer3 = new MessageAlertObserver(iphone);
                iphone.add(observer1);
                iphone.add(observer2);
                iphone.add(observer3);
                iphone.setData(100);

            }
}
