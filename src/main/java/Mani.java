import java.util.Scanner;

public class Mani {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Mani");
        System.out.println("What can I do for you?");

        String text = sc.nextLine();

        while (!text.equals("bye")) {
            System.out.println(text);
            text = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
