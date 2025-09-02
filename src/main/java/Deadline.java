import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task{
    private LocalDateTime deadline;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);

    public Deadline(String task, String date) {
        super(task);
        this.deadline = LocalDateTime.parse(date, inputFormatter);
    }

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
