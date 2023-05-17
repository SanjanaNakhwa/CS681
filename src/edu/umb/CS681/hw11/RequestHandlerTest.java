package edu.umb.CS681.hw11;

public class RequestHandlerTest {
    
        public static void main(String[] args) {
            // Create an AccessCounter instance
            AccessCounter counter = AccessCounter.getInstance();
    
            // Create 10 instances of RequestHandler
            RequestHandler[] handlers = new RequestHandler[10];
            for (int i = 0; i < handlers.length; i++) {
                handlers[i] = new RequestHandler(counter, "/Users/sanjananakhwa/Documents/OOPS/CS681Hws/src/edu/umb/CS681/hw11/FileFolder");
            }
    
            // Create 10 threads and start them
            Thread[] threads = new Thread[10];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(handlers[i]);
                threads[i].start();

            }
            
            // Wait for all threads to finish
            for (int i = 0; i < threads.length; i++) {
                    handlers[i].setDone();
                    threads[i].interrupt();
            }
        }
    }
    