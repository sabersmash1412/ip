import java.util.Scanner;

public class Mani {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] memory = new String[100];
        int count = 0;

        System.out.println("Hello! I'm Mani");
        System.out.println("What can I do for you?");

        String text = sc.nextLine();

        while (!text.equals("bye")) {
            if (text.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + memory[i]);
                }
                text = sc.nextLine();
                continue;
            }
            System.out.println("added: " + text);
            memory[count] = text;
            count++;
            text = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
