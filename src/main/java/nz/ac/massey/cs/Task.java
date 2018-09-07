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



    public Task(String name, String dueDate, String projectTitle) {
        this.name = name;
        this.dueDate = dueDate;
        this.projectTitle = projectTitle;
        this.description = "";
        this.completed = false;
        this.activeTasks = true;
    }
    public boolean isComplete() {
        return completed;
    }
    public boolean isActive() {
        return activeTasks;
    }
    public boolean getActiveTasks() {
        return activeTasks;
    }
    public boolean isAllTasks() {
        return activeTasks;
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
