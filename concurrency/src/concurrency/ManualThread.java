package concurrency;

import java.util.concurrent.TimeUnit;

public class ManualThread extends Thread {

    private int sleepInterval = 0;
    private String currThreadName;

    public ManualThread() {}

    public ManualThread(int sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    @Override
    public void run() {
        try {
            currThreadName = Thread.currentThread().getName();
            System.out.println("ManualThread started using: " + currThreadName);
            TimeUnit.MILLISECONDS.sleep(sleepInterval);
            System.out.println("ManualThread finished on: " + currThreadName);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
