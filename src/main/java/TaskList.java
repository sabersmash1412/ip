public class TaskList {
    private Task[] taskList;
    private int count;

    public TaskList() {
        this.taskList = new Task[100];
        this.count = 0;
    }

    public void addTask(Task task) {
        this.taskList[count] = task;
        this.count++;
    }

    public String markTask(int i) {
        return "Nice! I've marked this task as done:\n" + this.taskList[i - 1].markTask();
    }

    public String unmarkTask(int i) {
        return "OK, I've marked this task as not done yet:\n" + this.taskList[i - 1].unmarkTask();
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            String line = String.valueOf(i + 1) + "." + taskList[i] + "\n";
            result = result + line;
        }
        return result;
    }
}
