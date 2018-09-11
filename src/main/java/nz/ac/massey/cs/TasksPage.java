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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

		Label countLabel = new Label("count", new PropertyModel(collection, "taskCount"));
		add(countLabel);
		
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
								for(Task t: tasks) {
									t.setComplete(true);
									t.setActiveTask(false);
								}
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
				for(Task t: tasks) {
					if(t.isComplete() == true) {
						t.setComplete(false);
						t.setActiveTask(true);
					}
				}
			}
		});
		
		add(new Link<Void>("completedFilter") {
			@Override
			public void onClick() {
				List<Task> forAdding = new ArrayList<Task>();
				List<Task> forRemoval = new ArrayList<Task>();
				for(Task t: tasks) {
					if(t.isComplete() == false) {
						forRemoval.add(t);
					}
					else if(t.isComplete() == true) {
						forAdding.add(t);
					}
					else if(t.isActive() == false) {
						forRemoval.add(t);
					}
					else if(t.isActive() == true)  {
						forRemoval.add(t);
					}
				}
				tasks.removeAll(forRemoval);
				tasks.addAll(forAdding);
			    for(int i=0;i<tasks.size();i++){
			        for(int j=i+1;j<tasks.size();j++){
			            if(tasks.get(i).equals(tasks.get(j))){
			                tasks.remove(j);
			                j--;
			            }
			        }
			    }
			}
		});
		
		add(new Link<Void>("activeTasks") {
			@Override
			public void onClick() {
				List<Task> forRemovalA = new ArrayList<Task>();
				List<Task> forAddingA = new ArrayList<Task>();
				for(Task t: tasks) {
					if(t.isActive() == false) {
						forRemovalA.add(t);
					}
					else if(t.isActive() == true)  {
						forAddingA.add(t);
					}
					else if(t.isComplete() == false) {
						forAddingA.add(t);
					}
					else if(t.isComplete() == true) {
						forRemovalA.add(t);
					}
				}
				tasks.removeAll(forRemovalA);
				tasks.addAll(forAddingA);
			    for(int i=0;i<tasks.size();i++){
			        for(int j=i+1;j<tasks.size();j++){
			            if(tasks.get(i).equals(tasks.get(j))){
			                tasks.remove(j);
			                j--;
			            }
			        }
			    }
			}
		});
		
		add(new Link<Void>("allTasks") {
			@Override
			public void onClick() {
				List<Task> forAddingAll = new ArrayList<Task>();
				for(Task t: tasks) {
					if(t.isActive() == false) {
						forAddingAll.add(t);
					}
					else if(t.isActive() == true)  {
						forAddingAll.add(t);
					}
					else if(t.isComplete() == false) {
						forAddingAll.add(t);
					}
					else if(t.isComplete() == true) {
						forAddingAll.add(t);
					}
				}
				tasks.addAll(forAddingAll);
			    for(int i=0;i<tasks.size();i++){
			        for(int j=i+1;j<tasks.size();j++){
			            if(tasks.get(i).equals(tasks.get(j))){
			                tasks.remove(j);
			                j--;
			            }
			        }
			    }
			}
		});

		listForm.add(taskListView);

	}


	protected void clearComplete(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
