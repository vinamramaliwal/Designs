package PrintOddEven;

public class Main {

    public static void main(String[] args){
        OddEvenPrinter oddEvenPrinter = new OddEvenPrinter();
        ThreeThreads threeThreads = new ThreeThreads();

        Thread thread1 = new Thread(oddEvenPrinter::printEven);
        Thread thread2 = new Thread(oddEvenPrinter::printOdd);

        Thread thread3 = new Thread(threeThreads::printOneThread);
        Thread thread4 = new Thread(threeThreads::printTwoThread);
        Thread thread5 = new Thread(threeThreads::printThreeThread);

        thread3.start();
        thread4.start();
        thread5.start();
    }
}

/*
| Situation                                          | Will Threads Block Each Other?  |
| -------------------------------------------------- | ------------------------------- |
| Same lock object, different code blocks            | ✅ Yes                           |
| Different lock objects                             | ❌ No                            |
| Same object but `synchronized` on different fields | ❌ No (if fields are not shared) |

If the order of printing odd and even numbers does not matter, then you do not need wait() and notify()
 — just synchronized (or even atomic variables) is enough.

 Synchronised is used for mutual exclusion, Wait and notify is used for coordination.


 wait() temporarily pauses the thread AND releases the lock.
This allows another thread (like Thread-2) to enter the same synchronized block,
call notify(), and allow Thread-1 to resume later.

Only one thread at a time can hold the lock and be executing
 inside a synchronized block on a given object.
However:
If a thread calls wait(), it voluntarily gives up the lock, making room
for another thread to enter the synchronized block.

 So in effect:
Thread-1 enters synchronized(lock) ✅
It executes some code... then calls lock.wait() ❗
Thread-1 now releases the lock
It moves to the wait set
Thread-2 can now enter synchronized(lock) ✅
It can call lock.notify() to wake Thread-1
Once Thread-2 exits the synchronized block, Thread-1 is eligible to reacquire the lock and resume.

The notify:
Wakes up the sleeping thread.
That thread reacquires the lock.
Re-checks the condition (because of while).
Finds it true and proceeds.

 */