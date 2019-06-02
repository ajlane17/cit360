package mvc;

public class Duck implements AttackBehavior {
    @Override
    public void attack() {
        System.out.println("Hide, hide, hide!");
    }
}
