import java.util.Scanner;

public class Mani {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TaskList memory = new TaskList();

        System.out.println("Hello! I'm Mani");
        System.out.println("What can I do for you?");

        String text = sc.nextLine();

        while (!text.equals("bye")) {
            if (text.equals("list")) {
                System.out.println(memory);
                text = sc.nextLine();
                continue;
            } else if (text.contains("mark")) {
                char lastChar = text.charAt(text.length() - 1);
                int lastInt = Character.getNumericValue(lastChar);
                if (text.contains("unmark")) {
                    System.out.println(memory.unmarkTask(lastInt));
                } else {
                    System.out.println(memory.markTask(lastInt));
                }
                text = sc.nextLine();
                continue;
            }
            System.out.println("added: " + text);
            memory.addTask(new Task(text));
            text = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
