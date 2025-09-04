package seedu.mani.task;

public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    public Todo(String task, boolean mark){
        super(task, mark);
    }

    @Override
    public String getDetails() {
        return "T" + " | " + super.getDetails();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
