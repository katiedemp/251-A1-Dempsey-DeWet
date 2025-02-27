package nz.ac.massey.cs;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.ResourceReference;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TasksPage extends WebPage {

	private static final long serialVersionUID = 1L;


	public TasksPage() {

        add(new EntryForm("entryForm"));

        Form listForm = new Form("listForm");

        add(listForm);

		Date now = new Date();
		Label dateTimeLabel = new Label("datetime", now.toString());
		add(dateTimeLabel);
		
		WicketApplication app = (WicketApplication) this.getApplication();
		TaskList collection = app.getTaskList();
		List<Task> tasks = collection.getTasks();
		List<Task> tasksRemove = new ArrayList<Task>();

		Label countLabel = new Label("count", new PropertyModel(collection, "taskCount"));
		add(countLabel);
		Label enviroLabel = new Label("enviro", getApplication().getConfigurationType().toString());
		add(enviroLabel);
		
		PropertyListView taskListView =
				new PropertyListView("task_list", tasks) {
					private static final long serialVersionUID = 1L;

					@Override
					protected void populateItem(ListItem item) {

						item.add(new Label("name"));
						item.add(new Label("dueDate"));
						item.add(new Label("projectTitle"));
						item.add(new Label("description"));

						item.add(new AjaxCheckBox("completed") {
							@Override
							protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
								
							}
							
						});
					}
				};
				
				
		add(new Link<Void>("selectAll") {
			@Override
			public void onClick() {
				for(Task t: tasks) {
					t.setComplete(true);
					t.setActiveTask(false);
				}

			}
		});
		
		add(new Link<Void>("clearCompleted") {
            @Override
            public void onClick() {
                List<Task> forAdding = new ArrayList<Task>();
                tasks.addAll(tasksRemove);
                for(Task t: tasks) {
                    if(t.isComplete() == true) {
                        tasksRemove.add(t);
                    }
                    else if(t.isComplete() == false)  {
                        forAdding.add(t);
                    }
                    else if(t.isActive() == true) {
                        forAdding.add(t);
                    }
                    else if(t.isActive() == false)  {
                        tasksRemove.add(t);
                    }
                }
                tasks.removeAll(tasksRemove);
                tasks.addAll(forAdding);
                // creating a linkedhashset using the list
                Set<Task> tasksSet = new LinkedHashSet<Task>(tasks);
                // remove all the elements from the list
                tasks.clear();
                // add all the elements of the set to create a
                // list with out duplicates
                tasks.addAll(tasksSet);
            }
        });
		
		add(new Link<Void>("completedFilter") {
			@Override
			public void onClick() {
				List<Task> forAdding = new ArrayList<Task>();
				tasks.addAll(tasksRemove);
				for(Task t: tasks) {
					if(t.isComplete() == true) {
						forAdding.add(t);
					}
					else if(t.isComplete() == false)  {
						tasksRemove.add(t);
					}
					else if(t.isActive() == true) {
						tasksRemove.add(t);
					}
					else if(t.isActive() == false)  {
						forAdding.add(t);
					}
				}
				tasks.removeAll(tasksRemove);
				tasks.addAll(forAdding);
		        // creating a linkedhashset using the list
		        Set<Task> tasksSet = new LinkedHashSet<Task>(tasks);
		        // remove all the elements from the list 
		        tasks.clear();
		        // add all the elements of the set to create a
		        // list with out duplicates
		        tasks.addAll(tasksSet);
			}
		});
		
		add(new Link<Void>("activeTasks") {
			@Override
			public void onClick() {
				List<Task> forAdding = new ArrayList<Task>();
				tasks.addAll(tasksRemove);
				for(Task t: tasks) {
					if(t.isComplete() == false) {
						forAdding.add(t);
					}
					else if(t.isComplete() == true)  {
						tasksRemove.add(t);
					}
					else if(t.isActive() == false) {
						tasksRemove.add(t);
					}
					else if(t.isActive() == true)  {
						forAdding.add(t);
					}
				}
				tasks.removeAll(tasksRemove);
				tasks.addAll(forAdding);
		        // creating a linkedhashset using the list
		        Set<Task> tasksSet = new LinkedHashSet<Task>(tasks);
		        // remove all the elements from the list 
		        tasks.clear();
		        // add all the elements of the set to create a
		        // list with out duplicates
		        tasks.addAll(tasksSet);
			}
		});
		
		add(new Link<Void>("allTasks") {
			@Override
			public void onClick() {
				tasks.addAll(tasksRemove);
				List<Task> forAdding = new ArrayList<Task>();
				for(Task t: tasks) {
					if(t.isActive() == true) {
						forAdding.add(t);
					}
					if(t.isActive() == false) {
						forAdding.add(t);
					}
				}
				tasks.addAll(forAdding);
				
		        // creating a linkedhashset using the list
		        Set<Task> tasksSet = new LinkedHashSet<Task>(tasks);
		        // remove all the elements from the list 
		        tasks.clear();
		        // add all the elements of the set to create a
		        // list with out duplicates
		        tasks.addAll(tasksSet);
			}
		});

        add(new Link<Void>("exportbinary") {
            @Override
            public void onClick() {
                File outputfile = new File("tasks.bn");
                try {
                    FileOutputStream fos = new FileOutputStream(outputfile);
                    ObjectOutputStream oss = new ObjectOutputStream(fos);
                    oss.writeObject(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                File outputfile2 = new File("removedTasks.bn");
                try {
                    FileOutputStream fos = new FileOutputStream(outputfile2);
                    ObjectOutputStream oss = new ObjectOutputStream(fos);
                    oss.writeObject(tasksRemove);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        add(new Link<Void>("importbinary") {
            @Override
            public void onClick() {
                //inspiration from https://www.youtube.com/watch?v=qo9S2CeoqQE&pbjreload=10
                File f = new File("tasks.bn");
                try {
                    FileInputStream fis = new FileInputStream(f);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    List<Task> savedTasks = (List<Task>) ois.readObject();
                    tasks.clear();
                    tasks.addAll(savedTasks);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }

                File f2 = new File("removedTasks.bn");
                try {
                    FileInputStream fis = new FileInputStream(f2);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    List<Task> savedTasks = (List<Task>) ois.readObject();
                    tasksRemove.clear();
                    tasksRemove.addAll(savedTasks);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }


            }
        });

        add(new Link<Void>("importToDo") {
            @Override
            public void onClick() {
                try {
                    importTodoFile itf = new importTodoFile();
                    tasks.addAll(itf.returnTaskList());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

		listForm.add(taskListView);

	}

}
