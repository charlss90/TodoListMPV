package app.chraz.todolistmpv.entities;

import android.content.Intent;
import android.view.View;

/**
 * Created by Carlos E. Pazmiño Peralta on 10/3/15.
 */
public interface ITodoMainPresenter {

    void addTodo(String title, String description);

    void addTodoByIntent(Intent data);

    boolean removeTodoByPosition(int position);

    int getIndexOfTodoListByView(View view);

}
