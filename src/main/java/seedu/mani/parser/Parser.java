package seedu.mani.parser;
import seedu.mani.task.TaskList;
import seedu.mani.ui.Ui;
import seedu.mani.task.Todo;
import seedu.mani.task.Event;
import seedu.mani.task.Deadline;

/**
 * The Parser class is responsible for interpreting user commands.
 * It takes the raw string command, breaks it down and facilitates
 * the necessary output.
 */
public class Parser {

    /**
     * Parses through user's command and executes the operation as per input
     *
     * @param userCommand
     * @param ui
     * @param memory
     * @return
     */
    public static boolean parse(String userCommand, Ui ui, TaskList memory) {
        String[] words = userCommand.trim().split(" ", 2);
        String firstWord = words[0];
        String remainingMessage = words.length > 1 ? words[1] : "";

        switch (firstWord) {
            case "bye":
                return true;

            case "list":
                ui.systemMessage(memory.toString());
                return false;

            case "mark":
                String[] markParts = remainingMessage.split(" ");
                int markInt = Integer.parseInt(markParts[0]);
                ui.systemMessage(memory.markTask(markInt));
                return false;

            case "unmark":
                String[] unmarkParts = remainingMessage.split(" ");
                int unmarkInt = Integer.parseInt(unmarkParts[0]);
                ui.systemMessage(memory.unmarkTask(unmarkInt));
                return false;

            case "todo":
                if (remainingMessage.trim().isEmpty()) {
                    ui.systemMessage("Error: There is no task provided, description is empty.");
                    return false;
                }
                remainingMessage = remainingMessage.trim();
                ui.systemMessage(memory.addTask(new Todo(remainingMessage)));
                return false;

            case "deadline":
                if (remainingMessage.trim().isEmpty()) {
                    ui.systemMessage("Error: There is no task provided, description is empty.");
                    return false;
                }
                if (!remainingMessage.contains("/by")) {
                    ui.systemMessage("Error: Use command /by");
                    return false;
                }
                int index = remainingMessage.indexOf("/by");
                String task = remainingMessage.split("/")[0].trim();
                if (task.isEmpty()) {
                    ui.systemMessage("Error: No task provided.");
                    return false;
                }
                String date = remainingMessage.substring(index + 3).trim();
                if (date.isEmpty()) {
                    ui.systemMessage("Error: No deadline provided.");
                    return false;
                }
                ui.systemMessage(memory.addTask(new Deadline(task, date)));
                return false;

            case "event":
                if (remainingMessage.trim().isEmpty()) {
                    ui.systemMessage("Error: There is no task provided, description is empty.");
                    return false;
                }
                if (!remainingMessage.contains("/from") || !remainingMessage.contains("/to")) {
                    ui.systemMessage("Error: Use commands /from and /to");
                    return false;
                }
                String eventTask = remainingMessage.split("/")[0].trim();
                if (eventTask.isEmpty()) {
                    ui.systemMessage("Error: No task provided.");
                    return false;
                }
                int firstIndex = remainingMessage.indexOf("/from") + 5;
                int secondIndex = remainingMessage.indexOf("/to");
                String from = remainingMessage.substring(firstIndex, secondIndex).trim();
                String to = remainingMessage.substring(secondIndex + 3).trim();
                if (from.isEmpty() || to.isEmpty()) {
                    ui.systemMessage("Error: From or To is not provided.");
                    return false;
                }
                ui.systemMessage(memory.addTask(new Event(eventTask, from, to)));
                return false;

            case "delete":
                String[] parts = remainingMessage.split(" ");
                int lastInt = Integer.parseInt(parts[0]);
                ui.systemMessage(memory.deleteTask(lastInt - 1));
                return false;

            default:
                ui.errorMessage("There is no valid command provided.");
                return false;
        }
    }
}
