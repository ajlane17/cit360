package mvc;

public class MarineView {

    public void displayStatus(Marine marine) {
        System.out.println("Name: " + marine.getName() + ", Deployed: " + marine.getDeployed() + ", Attacking: " + marine.getAttacking());
    }
}
