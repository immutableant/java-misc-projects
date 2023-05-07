# Concurrent Number Printer

This project demonstrates how to print numbers from 0 to 99 concurrently using 10 different threads in Java. The goal is to showcase the usage of `ConcurrentLinkedQueue`, `ExecutorService`, and `ReentrantLock` to manage concurrent tasks and ensure thread safety.

## Features

- Utilizes an `ExecutorService` with a fixed thread pool of 10 threads.
- Stores numbers in a `ConcurrentLinkedQueue` to ensure thread-safe access.
- Uses a `ReentrantLock` to synchronize the printing of numbers.

## Usage

1. Compile the `ConcurrentNumberPrinter.java` file:
```sh
javac ConcurrentNumberPrinter.java
```

2. Run the compiled program:
```sh
java ConcurrentNumberPrinter
``` 

This will print the numbers from 0 to 99, without repetition, using 10 concurrent threads. The numbers can be printed in any order.


## Customization

To print a different range of numbers or use a different number of threads, modify the loop bounds when populating the `ConcurrentLinkedQueue` and creating the thread pool in the `main` method.

## Questions & Answers

Below is a list of questions and answers related to the project to help deepen your understanding of the concepts and techniques used:


1. **Q:** Why did you choose to use ConcurrentLinkedQueue instead of another data structure?
</br>**A:** ConcurrentLinkedQueue was chosen because it provides a thread-safe, non-blocking data structure, ensuring that multiple threads can access it concurrently without data corruption.

2. **Q:** What are the advantages of using an ExecutorService with a fixed thread pool?
</br>**A:** Using an ExecutorService with a fixed thread pool allows for efficient resource usage by limiting the number of active threads and reusing them for multiple tasks.

3. **Q:** Can you explain the purpose of the ReentrantLock in this solution?
</br>**A:** The ReentrantLock ensures that only one thread can print a number at a time, avoiding intermingled output from different threads.

4. **Q:** How does the ConcurrentLinkedQueue ensure thread safety?
</br>**A:** ConcurrentLinkedQueue ensures thread safety using non-blocking algorithms with atomic operations, avoiding the need for explicit synchronization.

5. **Q:** What would happen if you didn't use a lock for printing the numbers?
</br>**A:** If no lock was used for printing, the output from different threads could intermingle, leading to garbled output and potential confusion.

6. **Q:** Are there any other synchronization methods you could use instead of a ReentrantLock?
</br>**A:** Alternatives to ReentrantLock include using synchronized blocks, Semaphore, or AtomicInteger for managing access to shared resources.

7. **Q:** Can you explain the difference between ExecutorService.submit() and ExecutorService.execute()?
</br>**A:** ExecutorService.submit() returns a Future object that allows you to retrieve the result or cancel the task, while ExecutorService.execute() takes a Runnable and doesn't return a result.

8. **Q:** How would you modify this code to handle a larger range of numbers or a different number of threads?
</br>**A:** To handle a larger range of numbers or a different number of threads, simply adjust the loop bounds when populating the queue or creating the thread pool.

9. **Q:** How would you test the correctness and performance of this code?
</br>**A:** To test correctness, ensure that all numbers are printed without repetition. For performance, measure the execution time and observe the behavior under varying numbers of threads or tasks.

10. **Q:** What are the possible performance bottlenecks in this implementation?
</br>**A:** Possible performance bottlenecks include contention on the lock and the overhead of creating and managing threads.

11. **Q:** How does the Java Memory Model affect the behavior of this code?
</br>**A:** The Java Memory Model affects the behavior of this code by providing guarantees on the visibility and ordering of memory operations across threads, ensuring correct execution.

12. **Q:** Can you explain the difference between Runnable and Callable? Which one would you use if you needed to return a result from each task?
</br>**A:** Runnable represents a task that doesn't return a result, while Callable represents a task that returns a result. If you needed to return a result from each task, you would use Callable.

13. **Q:** How would you handle exceptions within the run() method of the NumberPrinter class?
</br>**A:** To handle exceptions within the run() method, you can use a try-catch block to handle specific exceptions and take appropriate action, such as logging the error or terminating the task.

14. **Q:** Could you implement a solution that prints the numbers in a sorted order, while still using multiple threads?
</br>**A:** To print numbers in sorted order, you could use a CountDownLatch or a barrier synchronization mechanism to coordinate the threads, ensuring that each thread prints its assigned numbers in order.

15. **Q:** How would you handle cases where the number of threads is much larger than the number of tasks or vice versa?
</br>**A:** If the number of threads is much larger than the number of tasks, consider using a smaller thread pool. If the number of tasks is much larger than the number of threads, the tasks will be queued and executed as threads become available.

## License

This project is available under the [GNU GPLv3 License](/LICENSE).