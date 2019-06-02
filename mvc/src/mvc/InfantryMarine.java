package mvc;

public class InfantryMarine extends Marine {

    public InfantryMarine(String name) {
        setAttackBehavior(new Shoot());
        setName(name);
    }
}
