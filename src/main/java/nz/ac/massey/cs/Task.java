package nz.ac.massey.cs;

import java.io.Serializable;
import java.util.Date;

// This class models a Task item

public class Task implements Serializable {

    private String description;
    private boolean completed;
    private boolean activeTasks;
    private boolean allTasks;
    private String name;
    private String dueDate;
    private Integer id;
    private Date date;
    private String projectTitle;



    public Task(String name) {
        this.name = name;
        this.dueDate = "";
        this.projectTitle = "";
        this.description = "";
        this.completed = false;
        this.activeTasks = false;
    }
    public boolean isComplete() {
        return completed;
    }
    public boolean isActive() {
        return activeTasks;
    }
    public boolean isAllTasks() {
        return completed;
    }
    public String getDueDate() {
    	return dueDate;
    }
    public String getProjectTitle() {
    	return projectTitle;
    }
    public void setComplete(boolean complete) { completed = complete; }
    public void setActiveTasks(boolean active) { activeTasks = active; }
}
