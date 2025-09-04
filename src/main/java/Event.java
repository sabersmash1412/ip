import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);

    public Event(String task, String from, String to) {
        super(task);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

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
