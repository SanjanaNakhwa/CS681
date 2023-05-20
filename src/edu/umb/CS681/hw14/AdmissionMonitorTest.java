
package edu.umb.CS681.hw14;

public class AdmissionMonitorTest {
    public static void main(String[] args) {
        AdmissionMonitor monitor = new AdmissionMonitor();

        Thread[] threads = new Thread[15];
        Runnable[] handlers = new Runnable[5];

        //Threads 1 to 5 

        for (int i = 0; i < handlers.length; i++) {
            handlers[i] = new EntranceHandler(monitor);
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        //Threads 6 to 10
        
        for (int i = handlers.length; i < handlers.length * 2; i++) {
            handlers[i - handlers.length] = new ExitHandler(monitor);
            threads[i] = new Thread(handlers[i - handlers.length]);
            threads[i].start();
        }

        //Threads 11 to 15

        for (int i = handlers.length * 2; i < threads.length;i++) {
            handlers[i - handlers.length * 2] = new StatsHandler(monitor);
            threads[i] = new Thread(handlers[i - handlers.length * 2]);
            threads[i].start();
            }
        
        for (Runnable handler : handlers) {

            if (handler instanceof EntranceHandler) {
                EntranceHandler entranceHandler = (EntranceHandler) handler;
                entranceHandler.setDone();
            } 
            else if (handler instanceof ExitHandler) {
                ExitHandler exitHandler = (ExitHandler) handler;
                exitHandler.setDone();
            } 
            else if (handler instanceof StatsHandler) {
                StatsHandler statsHandler = (StatsHandler) handler;
                statsHandler.setDone();
            }
        }

        // Interrupts the threads
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
