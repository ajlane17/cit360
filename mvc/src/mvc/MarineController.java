package mvc;

public class MarineController {
    private Marine marineModel;
    private MarineView marineView;

    public MarineController(Marine marineModel, MarineView marineView) {
        this.marineModel = marineModel;
        this.marineView = marineView;
    }

    public void startAttack() {
        marineModel.getAttackBehavior().attack();
        marineModel.setAttacking(true);
    }

    public void stopAttack() {
        marineModel.setAttacking(false);
    }

    public void deploy() {
        marineModel.setDeployed(true);
    }

    public void bringHome() {
        marineModel.setDeployed(false);
    }

    public void updateDisplay() {
        marineView.displayStatus(marineModel);
    }
}
