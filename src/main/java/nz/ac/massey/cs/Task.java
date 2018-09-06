package nz.ac.massey.cs;

import java.io.Serializable;
import java.util.Date;

// This class models a Task item

public class Task implements Serializable {

    private String description;
    private boolean completed;
    private String name;
    private Integer id;
    private Date date;

    private String dueDate;
    private String projectTitle;



    public Task(String name) {
        this.name = name;
        this.description = "";
        this.completed = false;
    }
    public boolean isComplete() {
        return completed;
    }
    public String getDueDate() {
    	return dueDate;
    }
    public String getProjectTitle() {
    	return projectTitle;
    }
    public void setComplete(boolean complete) { completed = complete; }
}
