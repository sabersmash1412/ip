package seedu.mani.ui;

import seedu.mani.storage.Storage;
import seedu.mani.task.TaskList;

import seedu.mani.parser.Parser;

import java.io.IOException;

public class Mani {
    private TaskList memory;
    private Ui ui;

    public Mani(String filePath) {
        ui = new Ui();
        try {
            memory = new TaskList(Storage.loadTasks(filePath));
        } catch (IOException e) {
            ui.errorMessage(e.getMessage());
            memory = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();

        boolean endMani = Parser.parse(ui.readCommand(), ui, memory);
        while (!endMani) {
            endMani = Parser.parse(ui.readCommand(), ui, memory);
        }

        ui.goodbyeMessage();
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Mani("data/tasks.txt").run();
    }
}
