package Multithreading;

public class Tennis implements Runnable{

    Object lock;
    String s;
    Tennis(Object lock, String s){
        this.lock = lock;
        this.s = s;
    }

    @Override
    public void run() {
            while(true){
                synchronized (lock){

                    System.out.println(s);
                    lock.notify();               //calls second thread(in waiting status) to acquire lock

                    try {
                        Thread.sleep(2000); // thread 1 sleeps
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        lock.wait();               //thread1 waits for notification from second thread
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
    }
}
