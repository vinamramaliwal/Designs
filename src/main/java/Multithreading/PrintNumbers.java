package Multithreading;

public class PrintNumbers implements Runnable{
     private static int count=1;
    final Object lock;
    PrintNumbers(Object lock){
        this.lock=lock;
    }
    @Override
    public void run() {

                while(count<100) {
                    synchronized (lock) {
                System.out.println(count++);
                lock.notifyAll();

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }



            }
        }
    }
}
