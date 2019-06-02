package mvc;

public class CommsMarine extends Marine {

    public CommsMarine(String name) {
        setAttackBehavior(new Stab());
        setName(name);
    }
}
