package nz.ac.massey.cs;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// list of tasks managed by the web application

public class TaskList implements Serializable {
    private List<Task> collection;

    public TaskList() {
        collection = new LinkedList<Task>();
    }

    public List<Task> getTasks() {
        return collection;
    }

    public void addTask(Task task) {
        collection.add(task);
    }

    public void removeTask(Task task) {
        collection.remove(task);
    }
    
    public long getTaskCount() {
    	return collection.stream().filter(task -> task.isComplete() != true).count();
    }
    
    public Stream<Task> getActiveTasksFilter() {
    	return collection.stream().filter(task -> task.isActive() == true);
    }
}
