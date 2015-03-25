package app.chraz.todolistmpv.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import app.chraz.todolistmpv.entities.IMainView;
import app.chraz.todolistmpv.entities.ITodoMainPresenter;
import app.chraz.todolistmpv.model.Todo;
import app.chraz.todolistmpv.view.TodoAdapter;

/**
 * Created by Carlos E. Pazmiño Peralta on 10/3/15.
 */
public class TodoMainPresenter implements ITodoMainPresenter {

    IMainView todoActivity;

    TodoAdapter todoListAdapter;

    public TodoMainPresenter(IMainView todoActivity, TodoAdapter todos) {
        this.todoActivity = todoActivity;
        todoListAdapter = todos;
    }


    @Override
    public void addTodo(String title, String description) {
        if (title != null && !title.isEmpty()) {
            todoListAdapter.addTodo(title, description);
            todoActivity.clearTodoEditText();
        }
    }

    @Override
    public void addTodoByIntent(Intent data) {
        String title = data.getStringExtra("title");
        String description = data.getStringExtra("description");

        addTodo(title, description);
    }

    @Override
    public boolean removeTodoByPosition(int position) {
        boolean isDeleted = false;
        Todo todo = todoListAdapter.getItem(position);

        if (todo != null) {
            todoListAdapter.remove(todo);
            todo.delete();

            isDeleted = true;
        }

        return isDeleted;
    }

    @Override
    public int getIndexOfTodoListByView(View view) {
        ListView todoList = todoActivity.getTodoList();
        return todoList.indexOfChild(view);
    }
}
