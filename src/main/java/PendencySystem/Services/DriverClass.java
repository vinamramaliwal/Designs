package PendencySystem.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DriverClass {

    @Autowired
    TrackingServices trackingServices;

    public void start() {

        trackingServices.startTracking(1112,new ArrayList<>(Arrays.asList("UPI","Karnataka", "Bangalore")));
        trackingServices.startTracking(2451,new ArrayList<>(Arrays.asList("UPI","Karnataka", "Mysore")));
        trackingServices.startTracking(3421,new ArrayList<>(Arrays.asList("UPI","Rajasthan", "Jaipur")));
        trackingServices.startTracking(1221,new ArrayList<>(Arrays.asList("Wallet","Karnataka", "Bangalore")));
        System.out.println("Count=="+trackingServices.getCounts((Arrays.asList("UPI"))));//3
        System.out.println("Count=="+trackingServices.getCounts((Arrays.asList("UPI","Karnataka"))));//2
        System.out.println("Count=="+trackingServices.getCounts((Arrays.asList("Bangalore"))));//0
        trackingServices.startTracking(4221,new ArrayList<>(Arrays.asList("Wallet","Karnataka", "Bangalore")));
        trackingServices.stopTracking(1112);
        trackingServices.stopTracking(2451);
        System.out.println("Count=="+trackingServices.getCounts((Arrays.asList("UPI"))));//1
        System.out.println("Count=="+trackingServices.getCounts((Arrays.asList("Wallet"))));//2
        System.out.println("Count=="+trackingServices.getCounts((Arrays.asList("UPI","Karnataka", "Bangalore"))));//0


    }
}
