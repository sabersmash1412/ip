package seedu.mani.task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();

        String output = taskList.addTask(new Task("read book"));

        assertEquals(1, taskList.getNum());
        assertTrue(taskList.toString().contains("read book"));
        assertTrue(output.contains("I've added this task"));
    }

    @Test
    public void deleteTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("read book"));

        String output = taskList.deleteTask(0);

        assertEquals(0, taskList.getNum());
        assertFalse(taskList.toString().contains("read book"));
        assertTrue(output.contains("I've removed this task"));

    }
}
