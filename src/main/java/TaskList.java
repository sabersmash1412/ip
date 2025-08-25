import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int count;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.count = 0;
    }

    public String addTask(Task task) {
        this.taskList.add(task);
        this.count++;
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + String.valueOf(this.count) + " tasks in the list.";
    }

    public String deleteTask(int i) {
        Task tmp = this.taskList.get(i);
        this.taskList.remove(i);
        this.count--;
        return "Noted. I've removed this task:\n" + tmp.toString() + "\nNow you have " + String.valueOf(this.count) + " tasks in the list.";
    }

    public String markTask(int i) {
        return "Nice! I've marked this task as done:\n" + this.taskList.get(i - 1).markTask();
    }

    public String unmarkTask(int i) {
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
