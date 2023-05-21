package edu.umb.CS681.hw17.WithDeadlock;

public class DeadlockExample {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        Thread threadA = new Thread(() -> {
            bridge.crossBridgeA();
        });

        Thread threadB = new Thread(() -> {
            bridge.crossBridgeB();
        });

        threadA.start();
        threadB.start();
    }
}