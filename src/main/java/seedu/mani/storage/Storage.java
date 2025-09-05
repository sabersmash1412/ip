package seedu.mani.storage;

import seedu.mani.task.Deadline;
import seedu.mani.task.Event;
import seedu.mani.task.Task;
import seedu.mani.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class is responsible for file operations.
 * It ensures Task data is stored in a .txt after Mani is run.
 * It ensures that the Task data is retrieved when Mani is run again.
 */
public class Storage {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * When task list is changed, the data is overwritten in tasks.txt.
     *
     * @param filePath
     * @param textToAdd
     * @throws IOException
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * When task is added, the new task details are added to existing file.
     *
     * @param filePath
     * @param textToAppend
     * @throws IOException
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * When task is deleted, the data is deleted from tasks.txt.
     *
     * @param filePath
     * @param lineNum
     * @throws IOException
     */
    public static void deleteLine(String filePath, int lineNum) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        s.close();

        lines.remove(lineNum);

        String text = "";
        for (String line : lines) {
            text = text + line + "\n";
        }

        try {
            writeToFile(filePath, text);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * When task is marked as done, the task's status is marked done in tasks.txt.
     *
     * @param filePath
     * @param lineNum
     * @throws IOException
     */
    public static void markTask(String filePath, int lineNum) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        s.close();

        String targetLine = lines.get(lineNum - 1);
        String[] parts = targetLine.split(" \\| ");

        if (parts[1].equals("0")) {
            parts[1] = "1";
        } else if (parts[1].equals("1")) {
            parts[1] = "0";
        }

        String updatedLine = String.join(" | ", parts);

        lines.set(lineNum - 1, updatedLine);

        String text = "";
        for (String line : lines) {
            text = text + line + "\n";
        }

        try {
            writeToFile(filePath, text);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * When Mani is run, the task details stored in tasks.txt are retrieved,
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static ArrayList<Task> loadTasks(String filePath) throws IOException {
        File f = new File(filePath);
        File parentDir = f.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!f.exists()) {
            f.createNewFile();
            return new ArrayList<>();
        }

        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        s.close();

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            if (parts[0].equals("T")) {
                tasks.add(new Todo(parts[2], parts[1].equals("1")));
            } else if (parts[0].equals("D")) {
                tasks.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
            } else {
                tasks.add(new Event(parts[2], parts[3], parts[4], parts[1].equals("1")));
            }
        }

        return tasks;
    }

}
