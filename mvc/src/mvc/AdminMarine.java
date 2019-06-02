package mvc;

public class AdminMarine extends Marine {

    public AdminMarine(String name) {
        setAttackBehavior(new Duck());
        setName(name);
    }
}
