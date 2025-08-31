import java.io.IOException;

public class Deadline extends Task{
    private String deadline;

    public Deadline(String task, String date) {
        super(task);
        this.deadline = date;
    }

    @Override
    public String getDetails() {
        return "D" + " | " + super.getDetails() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
