package seedu.mani.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);

    /**
     * Constructs a new Deadline task with the specified description and date string.
     *
     * @param task
     * @param date
     */
    public Deadline(String task, String date) {
        super(task);
        this.deadline = LocalDateTime.parse(date, inputFormatter);
    }

    /**
     * Constructs a Deadline task with specified description, date string, and completion status.
     *
     * @param task
     * @param date
     * @param mark
     */
    public Deadline(String task, String date, boolean mark){
        super(task, mark);
        this.deadline = LocalDateTime.parse(date);
    }

    @Override
    public String getDetails() {
        return "D" + " | " + super.getDetails() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy ha", Locale.ENGLISH)) + ")";
    }
}
