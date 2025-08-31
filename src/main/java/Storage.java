import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

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

    public static ArrayList<Task> loadTasks(String filePath) throws IOException {
        File f = new File(filePath);
        File parentDir = f.getParentFile();

        if (!parentDir.exists()) {
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

    public static void main(String[] args) {

        String file2 = "./data/mani.txt";

        try {
            writeToFile(file2, "first ine" + System.lineSeparator() + "second line");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


        try {
            printFileContents("data/fruits.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}
