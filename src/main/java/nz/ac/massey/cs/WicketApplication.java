package nz.ac.massey.cs;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 */
public class WicketApplication extends WebApplication
{
    private TaskList tasks;

    /**
	 * @see org.apache.wicket.Application#getHomePage()
	 */

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return TasksPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		tasks = new TaskList();
		tasks.addTask(new Task("name1","DueDate1","ProjectTitle","desc"));
		tasks.addTask(new Task("name2","DueDate1","ProjectTitle","desc"));
		tasks.addTask(new Task("name3","DueDate1","ProjectTitle","desc"));
		tasks.addTask(new Task("name4","DueDate1","ProjectTitle","desc"));
	}
	//Change

	public TaskList getTaskList() {
		return tasks;
	}
}
