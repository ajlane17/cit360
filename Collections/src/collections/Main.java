package collections;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {



    public static void main(String[] args) {

        Input input = Input.getInstance();

        String response;
        String menu = ("COLLECTIONS!\n\n" +
                       "1.  List\n" +
                       "2.  Queue\n" +
                       "3.  Set\n\n");

        System.out.print(menu);

        response = input.getString("Select a menu item: ");

        System.out.println("You selected " +  response + ".");
    }

}
