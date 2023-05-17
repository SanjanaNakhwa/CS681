package edu.umb.CS681.hw14;

class ExitHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean done = false;

    public ExitHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (!done) {
            try {
                monitor.exit();
                Thread.sleep(1000);
            } 
            catch (InterruptedException exception) {
                System.out.println("ExitHandler interrupted");
                break;
            }
        }
    }

    public void setDone() {
        done = true;
    }
}
