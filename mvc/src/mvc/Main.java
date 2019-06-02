package mvc;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create a list of Marines
        List<Marine> marineUnit = new ArrayList<>();

        // Create a marine view
        MarineView marineView = new MarineView();
        MarineController marineController = null;

        marineUnit.add(new AdminMarine("Robert"));
        marineUnit.add(new CommsMarine("James"));
        marineUnit.add(new InfantryMarine("Luther"));
        marineUnit.add(new ReconMarine("Wayne"));

        // Deploy marines to deploy and attack
        System.out.println("\nDeploying marines and sending them in to attack!\n");

        for (Marine marine : marineUnit) {
            marineController = new MarineController(marine, marineView);
            marineController.deploy();
            marineController.startAttack();
            marineController.updateDisplay();
        }

        // Bring a marine home
        System.out.println("\nThe admin marine was injured, we're sending him home!\n");
        marineController = new MarineController(marineUnit.get(0), marineView);
        marineController.stopAttack();
        marineController.bringHome();
        marineController.updateDisplay();
    }
}
