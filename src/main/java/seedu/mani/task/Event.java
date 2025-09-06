package seedu.mani.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a specific from date and to date, representing an event.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy ha", Locale.ENGLISH);
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, from and to date string.
     *
     * @param task
     * @param from
     * @param to
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
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
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    @Override
    public String getDetails() {
        return "E" + " | " + super.getDetails() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(OUTPUT_FORMATTER) + " to: " + this.to.format(OUTPUT_FORMATTER) + ")";
    }
}
