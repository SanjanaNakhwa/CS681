package edu.umb.CS681.hw13;

import java.time.Duration;

public class WithdrawRunnable implements Runnable {
    private BankAccount account;
    private volatile boolean done;

    public WithdrawRunnable(BankAccount account) {
        this.account = account;
        this.done = false;
    }

    public void run() {
        try {
            while (!done) {
                account.withdraw(100);
                Thread.sleep(Duration.ofSeconds(2).toMillis());
            }
        } catch (InterruptedException exception) {
            // Handle interruption
            System.out.println("Withdraw thread interrupted");
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    public void setDone() {
        done = true;
    }
}
