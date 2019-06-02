package mvc;

public class ReconMarine extends Marine {

    public ReconMarine(String name) {
        setAttackBehavior(new Shoot());
        setName(name);
    }
}
