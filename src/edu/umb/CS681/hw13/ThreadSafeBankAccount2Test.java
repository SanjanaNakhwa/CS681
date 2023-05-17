
package edu.umb.CS681.hw13;

public class ThreadSafeBankAccount2Test {
    public static void main(String[] args) {
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();

        // Create and start multiple withdrawal and deposit threads
        Thread[] threads = new Thread[10];
        WithdrawRunnable[] withdrawRunnables = new WithdrawRunnable[5];
        DepositRunnable[] depositRunnables = new DepositRunnable[5];

        for (int i = 0; i < 5; i++) {
            withdrawRunnables[i] = new WithdrawRunnable(bankAccount);
            depositRunnables[i] = new DepositRunnable(bankAccount);

            threads[i] = new Thread(withdrawRunnables[i]);
            threads[i + 5] = new Thread(depositRunnables[i]);

            threads[i].start();
            threads[i + 5].start();
        }

        // Terminate withdrawal threads
        for (int i = 0; i < 5; i++) {
            withdrawRunnables[i].setDone();
        }

        // Terminate deposit threads
        for (int i = 0; i < 5; i++) {
            depositRunnables[i].setDone();
        }

        // Wait for withdrawal and deposit threads to complete
        for (Thread thread : threads) {
            
                thread.interrupt();
            } 
        }
}


