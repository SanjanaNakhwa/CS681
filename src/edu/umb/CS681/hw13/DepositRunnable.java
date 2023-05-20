package edu.umb.CS681.hw13;

import java.time.Duration;

public class DepositRunnable implements Runnable {
    private BankAccount account;
    private volatile boolean done;

    public DepositRunnable(BankAccount account) {
        this.account = account;
        this.done = false;
    }

    public void run() {
        while (!done) {
            try {
                account.deposit(100);
                Thread.sleep(Duration.ofSeconds(2).toMillis());
            } 
            catch (InterruptedException exception) {
                // Handles interruption
                System.out.println("Deposit thread interrupted");
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
    }
    
    public void setDone() {
        done = true;
    }
    
}
