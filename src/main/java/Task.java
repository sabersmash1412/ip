public class Task {
    private String task;
    private boolean mark;

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

    public String getDetails() {
        String num = mark ? "1" : "0";
        return num + " | " + task;
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
