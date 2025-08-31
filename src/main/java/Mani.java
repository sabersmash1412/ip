import java.io.IOException;
import java.util.Scanner;

public class Mani {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TaskList memory = new TaskList();
        try {
            memory = new TaskList(Storage.loadTasks("./data/mani.txt"));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Hello! I'm Mani");
        System.out.println("What can I do for you?");

        String text = sc.nextLine();

        while (!text.equals("bye")) {
            if (text.equals("list")) {
                System.out.println(memory);
                text = sc.nextLine();
                continue;
            } else if (text.contains("mark")) {
                String[] parts = text.split(" ");
                int lastInt = Integer.parseInt(parts[1]);
                if (text.contains("unmark")) {
                    System.out.println(memory.unmarkTask(lastInt));
                } else {
                    System.out.println(memory.markTask(lastInt));
                }
                text = sc.nextLine();
                continue;
            }

            if (text.contains("todo") || text.contains("deadline") || text.contains("event")) {
                String[] parts = text.split(" ", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    System.out.println("Error: There is no task provided, description is empty.");
                    text = sc.nextLine();
                    continue;
                }
            }

            if (text.contains("todo")) {
                text = text.substring(5).trim();
                System.out.println(memory.addTask(new Todo(text)));
                text = sc.nextLine();
                continue;
            } else if (text.contains("deadline")) {
                if (!text.contains("/by")) {
                    System.out.println("Error: Use command /by");
                    text = sc.nextLine();
                    continue;
                }
                int index = text.indexOf("/by");
                String task = text.split("/")[0].trim();
                if (task.length() < 10) {
                    System.out.println("Error: No task provided.");
                    text = sc.nextLine();
                    continue;
                }
                task = task.substring(9).trim();
                String date = text.substring(index + 3).trim();


                if (date.isEmpty()) {
                    System.out.println("Error: No deadline provided.");
                    text = sc.nextLine();
                    continue;
                }

                System.out.println(memory.addTask(new Deadline(task, date)));
                text = sc.nextLine();
                continue;
            } else if (text.contains("event")) {
                if (!text.contains("/from") || !text.contains("/to")) {
                    System.out.println("Error: Use commands /from and /to");
                    text = sc.nextLine();
                    continue;
                }

                String task = text.split("/")[0].trim();
                if (task.length() < 7) {
                    System.out.println("Error: No task provided.");
                    text = sc.nextLine();
                    continue;
                }
                task = task.substring(6).trim();
                int firstIndex = text.indexOf("/from") + 5;
                int secondIndex = text.indexOf("/to");
                String from = text.substring(firstIndex, secondIndex).trim();
                String to = text.substring(secondIndex + 3).trim();



                if (from.isEmpty() || to.isEmpty()) {
                    System.out.println("Error: From or To is not provided.");
                    text = sc.nextLine();
                    continue;
                }

                System.out.println(memory.addTask(new Event(task, from, to)));
                text = sc.nextLine();
                continue;
            }

            if (text.contains("delete")) {
                String[] parts = text.split(" ");
                int lastInt = Integer.parseInt(parts[1]);
                System.out.println(memory.deleteTask(lastInt - 1));
                text = sc.nextLine();
                continue;
            }

            System.out.println("Error: There is no valid command provided.");
            text = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
