package app.chraz.todolistmpv.entities;

import app.chraz.todolistmpv.model.Todo;

/**
 * Created by Carlos E. Pazmiño Peralta on 9/3/15.
 */
public interface ITodoCreator {

    void addTodo(String title, String description);

    void addTodoInPosition(int position, String title, String description);

}
