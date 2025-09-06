package seedu.mani.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy ha", Locale.ENGLISH);
    private LocalDateTime deadline;

    public Deadline(String task, String date) {
        super(task);
        this.deadline = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    public Deadline(String task, String date, boolean mark){
        super(task, mark);
        this.deadline = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    @Override
    public String getDetails() {
        return "D" + " | " + super.getDetails() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }
}
