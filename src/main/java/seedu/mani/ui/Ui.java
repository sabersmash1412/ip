package seedu.mani.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void welcomeMessage() {
        System.out.println("Hello! I'm mani.ui.Mani\n What can I do for you?");
    }

    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void systemMessage(String message) {
        System.out.println(message);
    }

    public void errorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
