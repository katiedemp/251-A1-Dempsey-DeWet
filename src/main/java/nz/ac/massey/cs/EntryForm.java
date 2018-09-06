package nz.ac.massey.cs;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

// form with fields for adding a task item

public class EntryForm extends Form<Void> {

    private RequiredTextField nameField;
    private RequiredTextField dueDateField;
    private RequiredTextField projectTitleField;
    private RequiredTextField descriptionField;


    public EntryForm(String id) {
        super(id);
        nameField = new RequiredTextField("name", Model.of(""));
        dueDateField = new RequiredTextField("dueDate", Model.of(""));
        projectTitleField = new RequiredTextField("projectTitle", Model.of(""));
        descriptionField = new RequiredTextField("description", Model.of(""));

        add(nameField);
        add(dueDateField);
        add(projectTitleField);
        add(descriptionField);

    }

    // adds the task when the form is submitted (by clicking the Add button)
    protected void onSubmit() {
        super.onSubmit();
        String name = (String)nameField.getDefaultModelObject();
        String dueDate = (String)dueDateField.getDefaultModelObject();
        String projectTitle = (String)projectTitleField.getDefaultModelObject();
        String description = (String)descriptionField.getDefaultModelObject();

        descriptionField.clearInput();
        descriptionField.setModelObject(null);
        projectTitleField.clearInput();
        projectTitleField.setModelObject(null);
        dueDateField.clearInput();
        dueDateField.setModelObject(null);
        nameField.clearInput();
        nameField.setModelObject(null);

        WicketApplication app = (WicketApplication) this.getApplication();
        TaskList collection = app.getTaskList();
        collection.addTask(new Task(name));

    }
}
