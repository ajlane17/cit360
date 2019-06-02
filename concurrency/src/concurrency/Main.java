package concurrency;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // Display main thread name
        String currThreadName = Thread.currentThread().getName();
        System.out.println("Driver program is using: " + currThreadName);

        // Manually start a process in a new thread that runs for 5 seconds
        Thread manualThread = new ManualThread(5000);
        manualThread.start();

        // At one second in, throw some output from the main thread to show concurrency
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simple output from " + currThreadName + " to illustrate concurrency");

        // Throw a quick anonymous class implementing runnable on my main thread
        Runnable anonTask = () -> {
            System.out.println("anonTask is using: " + currThreadName);
        };
        anonTask.run();

        // Start another thread that takes a class implementing runnable
        new Thread(new RunnableTask()).start();

        // Create an ExecutorService and load it with tasks
        ExecutorService esSimpleTasks = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            Runnable task = null;
            if (i % 2 == 0) {
                esSimpleTasks.execute(new SimpleTaskOne());
            }
            else {
                esSimpleTasks.execute(new SimpleTaskTwo());
            }
        }

        esSimpleTasks.shutdown();

        try {
            esSimpleTasks.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Let's use our executor to safely update a value using synchronized and atomic variables
        Inventory inventory = new Inventory();

        Runnable addInventory = () -> {
            inventory.restockPencils(1);
            inventory.restockErasors(1);
            inventory.restockPens(1);
        };

        ExecutorService esConcurrentVarTasks = Executors.newFixedThreadPool(25);

        for (int i = 0; i < 1000000; i++) {
            esConcurrentVarTasks.submit(addInventory);
        }

        // Because the updates are running in other threads, I need to wait until they're done to get the value
        esConcurrentVarTasks.shutdown();

        try {
            esConcurrentVarTasks.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Pencils in inventory: " + inventory.pencilCount());
        System.out.println("Erasors in inventory: " + inventory.erasorCount());
        System.out.println("Pens in inventory: " + inventory.penCount());
        System.out.println("NOTE: Pens should illustrate synchronizing issues.");
    }
}
