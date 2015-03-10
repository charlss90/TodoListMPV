package app.chraz.todolistmpv.model;

/**
 * Created by Carlos E. Pazmiño Peralta on 8/03/15.
 */
public class Todo {

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Todo() {

    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
