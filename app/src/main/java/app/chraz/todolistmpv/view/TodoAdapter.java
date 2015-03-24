package app.chraz.todolistmpv.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.chraz.todolistmpv.R;
import app.chraz.todolistmpv.entities.ITodoCreator;
import app.chraz.todolistmpv.model.Todo;

/**
 * Created by chraz on 8/03/15.
 */
public class TodoAdapter extends ArrayAdapter<Todo> implements ITodoCreator {

    private final ArrayList<Todo> todoList;
    private final Context context;

    private int firstPosition = 0;

    public TodoAdapter(Context context, ArrayList<Todo> todoList) {
        super(context, R.layout.todo_row, todoList);
        this.todoList = todoList;
        this.context = context;

        fillTodoList();
        notifyDataSetChanged();
    }

    private void fillTodoList() {
        for (Todo newTodo : Todo.listAll(Todo.class)) {
            todoList.add(0, newTodo);
        }
    }

    @Override
    public void remove(Todo todo) {
        super.remove(todo);
        todo.delete();
        notifyDataSetChanged();
    }

    @Override
    public void addTodo(String title, String description) {
        this.addTodoInPosition(firstPosition, title, description);
    }

    @Override
    public void addTodoInPosition(int position, String title, String description) {
        Todo newTodo = createTodo(title, description);
        this.todoList.add(position, newTodo);
        this.notifyDataSetChanged();
    }

    private Todo createTodo(String title, String description) {
        Todo newTodo = new Todo(title, description);
        newTodo.save();
        return newTodo;
    }

    TextView title;
    TextView description;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View todoView = getTodoView(parent);

        inflateTodoView(getTodoByPosition(position));

        return todoView;
    }



    private View getTodoView(ViewGroup parent) {
        View todoView = getInflater().inflate(R.layout.todo_row, parent, false);
        setComponentsOfView(todoView);
        return todoView;
    }

    private LayoutInflater getInflater() {
        return (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void setComponentsOfView(View todoView) {
        title = (TextView) todoView.findViewById(R.id.title_row);
        description = (TextView) todoView.findViewById(R.id.description_row);
    }

    private Todo getTodoByPosition(int position) {
        return todoList.get(position);
    }

    private void inflateTodoView(Todo todo) {
        title.setText(todo.getTitle());
        showDescriptionIfNecessary(todo);
    }

    private void showDescriptionIfNecessary(Todo todo) {
        if (todo.getDescription().isEmpty()) {
            description.setVisibility(View.GONE);
        } else {
            description.setText(todo.getDescription());
            description.setVisibility(View.VISIBLE);
        }
    }
}
