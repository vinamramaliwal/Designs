package Multithreading;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class main {

   public static void main(String args[]) {

      Object lock = new Object();
      /* Thread thread1 = new Thread(new Tennis(lock, "PING"));
       Thread thread2 = new Thread(new Tennis(lock, "PONG"));
       thread1.start();
       thread2.start();*/

     /*  Thread thread1 = new Thread(new PrintNumbers(lock));
       Thread thread2 = new Thread(new PrintNumbers(lock));
       thread1.start();
       thread2.start();
       try {
           thread1.join();
           thread2.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }*/

       StreamAPIAddress address1= new StreamAPIAddress("AMROHA","244221");
       StreamAPIAddress address2= new StreamAPIAddress("NOIDA","123456");
       StreamAPICustomer customer1 = new StreamAPICustomer("Vinamra","vinamramaliwal@gmail.com", Arrays.asList(address1,address2));
       List<StreamAPICustomer> customerList = Arrays.asList(customer1);

      List<String> cities = customerList.stream().flatMap(customer -> customer.getAllAddress().stream()).map(StreamAPIAddress::getCity).collect(Collectors.toList());
      System.out.println(cities);




   }

}
