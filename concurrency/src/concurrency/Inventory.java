package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class Inventory {
    private int pencils;
    private AtomicInteger erasors = new AtomicInteger(0);
    private int pens;

    public synchronized void restockPencils(int increase) {
        pencils = pencils + increase;
    }

    public int pencilCount() {
        return pencils;
    }

    public void restockErasors(int increase) {
        erasors.addAndGet(increase);
    }

    public int erasorCount() {
        return erasors.intValue();
    }

    public void restockPens(int increase) {
        pens = pens + increase;
    }

    public int penCount() {
        return pens;
    }
}
