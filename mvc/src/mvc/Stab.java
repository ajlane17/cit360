package mvc;

public class Stab implements AttackBehavior {
    @Override
    public void attack() {
        System.out.println("Stab, stab, stab!");
    }
}
