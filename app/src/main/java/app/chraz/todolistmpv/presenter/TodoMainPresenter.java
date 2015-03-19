package app.chraz.todolistmpv.presenter;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collection;

import app.chraz.todolistmpv.entities.IMainView;
import app.chraz.todolistmpv.entities.ITodoMainPresenter;
import app.chraz.todolistmpv.model.Todo;
import app.chraz.todolistmpv.view.TodoAdapter;

/**
 * Created by Carlos E. Pazmiño Peralta on 10/3/15.
 */
public class TodoMainPresenter implements ITodoMainPresenter {

    IMainView todoActivity;

    TodoAdapter todoList;

    public TodoMainPresenter(IMainView todoActivity, TodoAdapter todos) {
        this.todoActivity = todoActivity;
        todoList = todos;
    }


    @Override
    public void addTodo(String title, String description) {
        if (title != null && !title.isEmpty()) {
            todoList.addTodo(title, description);
            todoActivity.clearTodoEditText();
        }
    }

    @Override
    public void addTodoByIntent(Intent data) {
        String title = data.getStringExtra("title");
        String description = data.getStringExtra("description");

        addTodo(title, description);
    }
}
