## Race Condition Explanation

The Stock class represents a shared buffer(stock) used by multiple producer and consumer threads. However, there is a race condition in the code, which can lead to data corruption or incorrect behavior.

In the `produce` method:
- Multiple producer threads can simultaneously check if the stock is full and try to add items concurrently.
- Concurrent modification of the stock can result in data corruption or incorrect ordering of items.

In the `consume` method:
- Multiple consumer threads can simultaneously check if the stock is empty and try to remove items concurrently.
- Concurrent modification of the stock can lead to incorrect consumption or missing items.

To fix the race condition, the following changes were made:
- The `produce` and `consume` methods were declared as synchronized.
- Synchronization ensures that only one thread can execute the methods at a time, preventing concurrent modifications.
- Synchronizing the methods ensures thread-safe access to the shared stock.

With these modifications, the race condition is resolved, and the code is safe for concurrent execution by multiple producer and consumer threads.