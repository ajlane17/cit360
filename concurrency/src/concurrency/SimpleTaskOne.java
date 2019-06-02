package concurrency;

public class SimpleTaskOne implements Runnable {

    private String currThreadName;

    @Override
    public void run() {
        currThreadName = Thread.currentThread().getName();
        System.out.println("SimpleTaskOne started using: " + currThreadName);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SimpleTaskOne finished on: " + currThreadName);
    }
}
