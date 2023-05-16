package edu.umb.CS681.hw13;

public class ThreadSafeBankAccount2Test {
    public static void main(String[] args) {
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();


        // Create and start multiple withdrawal threads
        WithdrawRunnable[] withdrawRunnables = new WithdrawRunnable[2];
        Thread[] withdrawThreads = new Thread[2];
        for (int i = 0; i < withdrawThreads.length; i++) {
            withdrawRunnables[i] = new WithdrawRunnable(bankAccount);
            withdrawThreads[i] = new Thread(withdrawRunnables[i]);
            withdrawThreads[i].start();
        }

        // Create and start multiple deposit threads
        DepositRunnable[] depositRunnables = new DepositRunnable[2];
        Thread[] depositThreads = new Thread[2];
        for (int i = 0; i < depositThreads.length; i++) {
            depositRunnables[i] = new DepositRunnable(bankAccount);
            depositThreads[i] = new Thread(depositRunnables[i]);
            depositThreads[i].start();
        }

        // Terminate withdrawal threads
        for (int i = 0; i < 2; i++) {
            try{
            withdrawRunnables[i].setDone();
            withdrawThreads[i].join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // Terminate deposit threads
        for (int i = 0; i < 2; i++) {
            try{
            depositRunnables[i].setDone();
            depositThreads[i].join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
