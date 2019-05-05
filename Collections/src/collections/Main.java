package collections;

import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        boolean endProgram = false;
        int response;

        Input input = Input.getInstance();
        CollectionExercises collectionExercises = CollectionExercises.getInstance();

        // Let's store the menu items in an array!
        List<String> menuItems = new ArrayList<>();
        menuItems.add("List");
        menuItems.add("Set");
        menuItems.add("Map");
        menuItems.add("Queue");
        menuItems.add("Tree");
        menuItems.add("Exit");

        String menu = "\n\nCOLLECTIONS DEMO\n\n";

        // Build the menu by iterating over the list
        for (int i = 0; i < menuItems.size(); i++) {
            menu = menu.concat((i + 1) + ".  " + menuItems.get(i) + "\n");
        }

        menu = menu.concat("\nPlease type a menu #: ");

        // Loop the menu
        while (!endProgram) {

            response = input.getInt(menu) - 1;

            String selection;

            // Preform basic input validation
            if (response >= 0 && response <= menuItems.size()) {
                selection = menuItems.get(response);
            }
            else
            {
                selection = "";
            }

            // String based matching to allow for flexibility
            switch (selection) {
                case "List":
                    System.out.println("LIST is using an ArrayList...");
                    collectionExercises.listExercise();
                    break;
                case "Set":
                    System.out.println("SET is using a LinedHashSet...");
                    collectionExercises.setExercise();
                    break;
                case "Map":
                    System.out.println("SET is using a HashMap...");
                    collectionExercises.mapExercise();
                    break;
                case "Queue":
                    System.out.println("QUEUE (DEQUE) is using an ArrayDeque...");
                    collectionExercises.queueExercise();
                    break;
                case "Tree":
                    System.out.println("TREE is using a TreeSet...");
                    collectionExercises.treeExercise();
                    break;
                case "Exit":
                    System.out.println("Thanks, goodbye!");
                    endProgram = true;
                    break;
                default:
                    System.out.println("INVALID - NOT A MENU ITEM");
                    break;
            }
        }
    }

}
