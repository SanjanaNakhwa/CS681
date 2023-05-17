package edu.umb.CS681.hw14;

class StatsHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean done = false;

    public StatsHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (!done) {
            try {
                monitor.countCurrentVisitors();
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                System.out.println("StatsHandler interrupted");
                break;
            }
        }
    }

    public void setDone() {
        done = true;
    }
}
