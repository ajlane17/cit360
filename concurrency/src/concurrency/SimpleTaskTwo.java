package concurrency;

public class SimpleTaskTwo implements Runnable {

    private String currThreadName;

    @Override
    public void run() {
        currThreadName = Thread.currentThread().getName();
        System.out.println("SimpleTaskTwo started using: " + currThreadName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SimpleTaskTwo finished on: " + currThreadName);
    }
}
