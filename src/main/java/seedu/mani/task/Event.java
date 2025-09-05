package seedu.mani.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a specific from date and to date, representing an event.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);

    /**
     * Constructs a new Event task with the specified description, from and to date string.
     *
     * @param task
     * @param from
     * @param to
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * Constructs a Deadline task with specified description, from and to date string, and completion status.
     *
     * @param task
     * @param from
     * @param to
     * @param mark
     */
    public Event(String task, String from, String to, boolean mark){
        super(task, mark);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String getDetails() {
        return "E" + " | " + super.getDetails() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy ha", Locale.ENGLISH)) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }
}
