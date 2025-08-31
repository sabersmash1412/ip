import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int count;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.count = 0;
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.count = taskList.size();
    }

    public String addTask(Task task) {
        this.taskList.add(task);
        this.count++;
        try {
            Storage.appendToFile("./data/mani.txt", task.getDetails() + "\n");
        } catch (IOException e) {
            System.out.println("Error in saving to file: " + e.getMessage());
        }
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + String.valueOf(this.count) + " tasks in the list.";
    }

    public String deleteTask(int i) {
        Task tmp = this.taskList.get(i);
        try {
            Storage.deleteLine("./data/mani.txt", i);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        this.taskList.remove(i);
        this.count--;
        return "Noted. I've removed this task:\n" + tmp.toString() + "\nNow you have " + String.valueOf(this.count) + " tasks in the list.";
    }

    public String markTask(int i) {
        try {
            Storage.markTask("./data/mani.txt", i);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "Nice! I've marked this task as done:\n" + this.taskList.get(i - 1).markTask();
    }

    public String unmarkTask(int i) {
        try {
            Storage.markTask("./data/mani.txt", i);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "OK, I've marked this task as not done yet:\n" + this.taskList.get(i - 1).unmarkTask();
    }


    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            String line = String.valueOf(i + 1) + "." + taskList.get(i) + "\n";
            result = result + line;
        }
        return result;
    }
}
