package nz.ac.massey.cs;

import java.io.Serializable;
import java.util.Date;

// This class models a Task item

public class Task implements Serializable {

    private String description;
    private boolean completed;
    private boolean activeTask;
    private String name;
    private String dueDate;
    private Integer id;

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    private String projectTitle;


    public Task(String name, String dueDate, String projectTitle, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.projectTitle = projectTitle;
        this.description = description; 
        this.completed = false;
        this.activeTask = true;
    }
    public boolean isComplete() {
        return completed;
    }
    public boolean isActive() {
        return activeTask;
    }
    public String getDueDate() {
    	return dueDate;
    }
    public String getProjectTitle() {
    	return projectTitle;
    }
    public String getDescription() {
    	return description;
    }
    public void setComplete(boolean complete) { completed = complete; }
    public void setActiveTask(boolean active) { activeTask = active; }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }


    public void setCompleted(boolean b) {
        completed = b;
    }
}
