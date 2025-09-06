package seedu.mani.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import seedu.mani.task.Task;
import seedu.mani.task.Todo;

public class StorageTest {
    private static final String TEST = "test.txt";

    @BeforeEach
    public void start() throws IOException {
        Storage.writeToFile(TEST, "");
    }

    @AfterEach
    public void end() {
        File f = new File(TEST);
        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    public void appendToFileTest() throws IOException {
        Storage.appendToFile(TEST, "test first line\n");
        Storage.appendToFile(TEST, "test second line\n");

        String content = Files.readString(new File(TEST).toPath());
        assertTrue(content.contains("test first line"));
        assertTrue(content.contains("test second line"));
    }

    @Test
    public void loadTaskTest() throws IOException {
        String data = "T | 0 | read book\nT | 1 | return book\n";
        Storage.writeToFile(TEST, data);

        ArrayList<Task> tasks = Storage.loadTasks(TEST);

        assertEquals(2, tasks.size());
        assertEquals("T | 0 | read book", tasks.get(0).getDetails());
        assertEquals("T | 1 | return book", tasks.get(1).getDetails());
        assertFalse(tasks.get(0).isDone());
        assertTrue(tasks.get(1).isDone());
    }

}
