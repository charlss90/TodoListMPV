package app.chraz.todolistmpv.entities;

import android.content.Intent;

/**
 * Created by Carlos E. Pazmiño Peralta on 10/3/15.
 */
public interface ITodoMainPresenter {

    void addTodo(String title, String description);

    void addTodoByIntent(Intent data);

}
