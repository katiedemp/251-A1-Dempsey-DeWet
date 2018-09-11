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
    private Date date;
    private String projectTitle;


    public Task(String name, String dueDate, String projectTitle) {
        this.name = name;
        this.dueDate = dueDate;
        this.projectTitle = projectTitle;
        this.description = "";
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
    public void setComplete(boolean complete) { completed = complete; }
    public void setActiveTask(boolean active) { activeTask = active; }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
	
}
