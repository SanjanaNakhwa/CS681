package edu.umb.CS681.hw14;

class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean done = false;

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (!done) {
            try {
                monitor.enter();
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                System.out.println("EntranceHandler interrupted");
                break;
            }
        }
    }

    public void setDone() {
        done = true;
    }
}
