package collections;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Input {
    private static Input inputInstance = null;
    private Scanner scanner;

    private Input() {
        scanner = new Scanner(System.in); }

    public static Input getInstance() {
        if (inputInstance == null) {
            inputInstance = new Input();
        }
        return inputInstance;
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        scanner.reset();
        return input;
    }

    public int getInt(String prompt) {
        int input;
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("\nINVALID - NOT A NUMBER");
            System.out.print(prompt);
            scanner.nextLine();
        }
        input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public List<String> getFileContents(String fileName) {
        List<String> fileContents = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                fileContents.add(fileScanner.next().toLowerCase());
            }
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return fileContents;
    }
}

