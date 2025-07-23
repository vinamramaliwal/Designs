package PrintOddEven;

public class ThreeThreads {
    final int max = 100;
    int curr = 0;

    int num = 1;
    Object lock = new Object();

    public void printOneThread(){

        synchronized (lock) {
            while (curr<max) {
                while (num != 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print("1 ");
                num++;
                curr = curr + 1;
                lock.notifyAll();

            }
        }
    }

    public void printTwoThread(){
        synchronized (lock) {
            while (curr<max) {
                while (num != 2) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print("2 ");
                num++;
                curr = curr + 1;
                lock.notifyAll();

            }
        }
    }

    public void printThreeThread(){
        synchronized (lock) {
            while (curr<max) {
                while (num != 3) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("3");
                num=1;
                curr = curr + 1;
                lock.notifyAll();

            }
        }

    }


}
