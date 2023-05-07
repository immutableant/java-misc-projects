
/**
 * Write a simple program to print the numbers from 0-99 concurrently from 10 different threads. 
 * The numbers can be printed in any order but must not be repeated. 
 * Use Java.
 * We're using ConcurrentLinkedQueue to store numbers, 
 * ExecutorService and Executors for thread management, 
 * and Lock and ReentrantLock to handle synchronization.
 */
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentNumberPrinter {

    public static void main(String[] args) {
        // The main method begins by creating an ExecutorService with a fixed thread
        // pool of 10 threads:
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // Next, we create a ConcurrentLinkedQueue to store the numbers from 0 to 99:
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        // We'll also need a ReentrantLock to ensure that each number is printed exactly
        // once:
        Lock printLock = new ReentrantLock();

        // We then populate the queue with numbers 0 to 99:
        for (int i = 0; i < 100; i++) {
            queue.add(i);
        }

        // Now, we'll submit 10 instances of the NumberPrinter task to the
        // ExecutorService:
        for (int i = 0; i < 10; i++) {
            executorService.submit(new NumberPrinter(queue, printLock));
        }

        // Once all tasks are submitted, we shut down the ExecutorService:
        executorService.shutdown();
    }

    static class NumberPrinter implements Runnable {
        // The class has two fields: a reference to the shared ConcurrentLinkedQueue and
        // a reference to the printLock:
        private final ConcurrentLinkedQueue<Integer> queue;
        private final Lock printLock;

        // In the constructor, we initialize these fields with the given queue and lock:
        public NumberPrinter(ConcurrentLinkedQueue<Integer> queue, Lock printLock) {
            this.queue = queue;
            this.printLock = printLock;
        }

        // The run method contains the main logic for the task. As long as the queue is
        // not empty, the task will continue polling numbers from the queue:
        @Override
        public void run() {
            while (!queue.isEmpty()) {
                // We retrieve and remove the head of the queue, if not null:
                Integer number = queue.poll();
                if (number != null) {
                    // We then acquire the printLock to ensure that only one thread can print at a
                    // time:
                    printLock.lock();
                    // In a try block, we print the thread name and the number:
                    try {
                        System.out.println("Thread: " + Thread.currentThread().getName() + ", Number: " + number);
                        // In a finally block, we release the printLock:
                    } finally {
                        printLock.unlock();
                    }
                }
            }
        }
    }
}
