package mvc;

public class Shoot implements AttackBehavior {
    @Override
    public void attack() {
        System.out.println("Bang, bang, bang!");
    }
}
