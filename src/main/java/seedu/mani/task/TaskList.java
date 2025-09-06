package seedu.mani.task;

import seedu.mani.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages a list of tasks and handles operations on them.
 */
public class TaskList {
    private static final String FILE_PATH = "./data/tasks.txt";
    private ArrayList<Task> taskList;
    private int count;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.count = 0;
    }

    /**
     * Constructs a TaskList from an existing list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.count = taskList.size();
    }

    /**
     * Adds a task to the list and updates storage.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        this.count++;
        try {
            Storage.appendToFile(FILE_PATH, task.getDetails() + "\n");
        } catch (IOException e) {
            System.out.println("Error in saving to file: " + e.getMessage());
        }
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + String.valueOf(this.count) + " tasks in the list.";
    }

    /**
     * Deletes a task from the list and updates storage.
     */
    public String deleteTask(int i) {
        Task tmp = this.taskList.get(i);
        try {
            Storage.deleteLine(FILE_PATH, i);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        this.taskList.remove(i);
        this.count--;
        return "Noted. I've removed this task:\n" + tmp.toString() + "\nNow you have " + String.valueOf(this.count) + " tasks in the list.";
    }

    /**
     * Marks a task as done and updates storage.
     */
    public String markTask(int i) {
        try {
            Storage.markTask(FILE_PATH, i);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "Nice! I've marked this task as done:\n" + this.taskList.get(i - 1).markTask();
    }

    /**
     * Marks a task as not done and updates storage.
     */
    public String unmarkTask(int i) {
        try {
            Storage.markTask(FILE_PATH, i);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "OK, I've marked this task as not done yet:\n" + this.taskList.get(i - 1).unmarkTask();
    }

    public int getNum() {
        return this.taskList.size();
    }

    public String findTask(String keyword) {
        String header = "Here are the matching tasks in your list:\n";
        String output = "";
        for (Task task : this.taskList) {
            if (task.toString().contains(keyword)) {
                output = output + task.toString() + "\n";
            }
        }
        return output.isEmpty() ? "No matching tasks" : header + output;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        return result.toString();
    }
}
