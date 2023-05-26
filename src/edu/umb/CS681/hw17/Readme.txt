##Deadlock Explanation

In the code with deadlock, a deadlock situation can occur when both threads from Group A and Group B attempt to acquire locks in a different order. This leads to a circular wait, where a vehicle from Group A acquires lockA and waits to acquire lockB, while simultaneously a vehicle from Group B has acquired lockB and is waiting to acquire lockA. As a result, neither vehicle can proceed, leading to a deadlock.

#Deadlock Removal
To remove the deadlock, the code was modified by introducing a timeout mechanism and enforcing an ordered lock acquisition strategy.

Lock Acquisition Order:

In the crossBridgeA method:
lockA is attempted to be acquired using tryLock(). If successful, the thread proceeds to acquire lockB.
If lockB is unavailable, the thread releases lockA and retries after a short sleep period before attempting to cross the bridge again.

In the crossBridgeB method:
lockB is attempted to be acquired using tryLock(). If successful, the thread proceeds to acquire lockA.
If lockA is unavailable, the thread releases lockB and retries after a short sleep period before attempting to cross the bridge again.

Lock Timeout:
The tryLock() method is used with a timeout period in both methods (crossBridgeA and crossBridgeB).
If a lock cannot be acquired within the specified time, the thread releases the acquired lock and retries after a short sleep.
This prevents threads from waiting indefinitely and allows them to make progress.
With these modifications, the potential for a circular wait and deadlock is eliminated. The threads attempt to acquire locks in a consistent and predefined order, preventing the possibility of a deadlock situation.
