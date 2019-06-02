package mvc;

public abstract class Marine {

    private AttackBehavior attackBehavior;
    private String name;
    private String rank = "Private";
    private String religion = "Christian";
    private Boolean isDeployed = false;
    private Boolean isAttacking = false;
    private int ammo;

    public AttackBehavior getAttackBehavior() {
        return attackBehavior;
    }

    public void setAttackBehavior(AttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Boolean getDeployed() {
        return isDeployed;
    }

    public void setDeployed(Boolean deployed) {
        isDeployed = deployed;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public Boolean getAttacking() {
        return isAttacking;
    }

    public void setAttacking(Boolean attacking) {
        isAttacking = attacking;
    }
}
