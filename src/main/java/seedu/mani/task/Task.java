package seedu.mani.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private String task;
    private boolean mark;

    /**
     * Constructs a new Task with the given description, initially not marked.
     *
     * @param task
     */
    public Task(String task) {
        assert task != null : "Task description is null";
        assert !task.trim().isEmpty() : "Task description is empty";
        this.task = task;
        this.mark = false;
    }

    /**
     * Constructs a Task with the given description and specified mark status.
     *
     * @param task
     * @param mark
     */
    public Task(String task, boolean mark) {
        assert task != null : "Task description is null";
        assert !task.trim().isEmpty() : "Task description is empty";
        this.task = task;
        this.mark = mark;
    }

    /**
     * Marks the task as done and returns its string representation.
     */
    public String markTask() {
        this.mark = true;
        assert this.isDone() : "Task is not marked";
        return this.toString();
    }

    /**
     * Marks the task as not done and returns its string representation.
     */
    public String unmarkTask() {
        this.mark = false;
        assert !this.isDone() : "Task is not unmarked";
        return this.toString();
    }

    /**
     * Returns a formatted string for storage purposes.
     */
    public String getDetails() {
        String status = mark ? "1" : "0";
        return status + " | " + task;
    }

    /**
     * Returns the completion status of the task.
     */
    public boolean isDone() {
        return this.mark;
    }

    /**
     * Returns a user-friendly string representation of the task.
     */
    @Override
    public String toString() {
        if (mark) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
