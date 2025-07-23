package PrintOddEven;

public class OddEvenPrinter {

    final int max = 2;
    int curr = 0;
    Object lock = new Object();

    public void printOdd(){

            synchronized (lock) {
                while (curr<max) {
                while (curr % 2 == 0) {
                    try {
                        lock.wait();
                        System.out.println("Hello-2");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Odd :: " + curr);
                curr = curr + 1;
                lock.notifyAll();
                System.out.println("Hello-1");

            }
        }
    }

    public void printEven(){
        synchronized (lock) {
            while (curr < max) {
                while (curr % 2 == 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Even :: " + curr);
                curr = curr + 1;
                lock.notifyAll();

            }
        }
    }
}
