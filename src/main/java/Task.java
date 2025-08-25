public class Task {
    private String task;
    public boolean mark;

    public Task(String task) {
        this.task = task;
        this.mark = false;
    }

    public String markTask() {
        this.mark = true;
        return this.toString();
    }

    public String unmarkTask() {
        this.mark = false;
        return this.toString();
    }

    @Override
    public String toString() {
        if (mark) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
