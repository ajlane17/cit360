package concurrency;

import java.util.concurrent.TimeUnit;

public class RunnableTask implements Runnable {

    private int sleepInterval = 0;
    private String currThreadName;

    public RunnableTask() {}

    public RunnableTask(int sleepInterval) {
        this.sleepInterval = sleepInterval;
    }
    @Override
    public void run() {
        try {
            currThreadName = Thread.currentThread().getName();
            System.out.println("RunnableTask started using: " + currThreadName);
            TimeUnit.MILLISECONDS.sleep(sleepInterval);
            System.out.println("RunnableTask finished on: " + currThreadName);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
