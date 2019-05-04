package collections;

import java.util.Scanner;

public final class Input {
    private static Input inputInstance = null;
    private Scanner scanner;

    private Input() { scanner = new Scanner(System.in); }

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
}

