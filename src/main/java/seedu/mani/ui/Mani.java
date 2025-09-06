package seedu.mani.ui;

import seedu.mani.storage.Storage;
import seedu.mani.task.TaskList;
import seedu.mani.parser.Parser;
import java.io.IOException;

/**
 * Main class for the Mani task management application.
 * Initializes the application and manages the main execution loop.
 */
public class Mani {
    private TaskList memory;
    private Ui ui;

    /**
     * Constructs a Mani instance and initializes the task list from storage.
     *
     * @param filePath
     */
    public Mani(String filePath) {
        ui = new Ui();
        try {
            memory = new TaskList(Storage.loadTasks(filePath));
        } catch (IOException e) {
            ui.errorMessage(e.getMessage());
            memory = new TaskList();
        }
    }

    /**
     * Runs the main application loop, processing user commands until exit.
     */
    public void run() {
        ui.welcomeMessage();

        boolean endMani = Parser.parse(ui.readCommand(), ui, memory);
        while (!endMani) {
            endMani = Parser.parse(ui.readCommand(), ui, memory);
        }

        ui.goodbyeMessage();
        ui.closeScanner();
    }

    /**
     * Main entry point of application.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Mani("data/tasks.txt").run();
    }
}
