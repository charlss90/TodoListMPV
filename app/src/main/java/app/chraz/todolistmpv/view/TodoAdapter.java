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
    }

    @Override
    public void addTodo(String title, String description) {
        this.addTodoInPosition(firstPosition, title, description);
    }

    @Override
    public void addTodoInPosition(int position, String title, String description) {
        this.todoList.add(position, new Todo (title, description));
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View todoView = inflater.inflate(R.layout.todo_row, parent, false);

        TextView title = (TextView) todoView.findViewById(R.id.title_row);
        TextView description = (TextView) todoView.findViewById(R.id.description_row);

        Todo todo = todoList.get(position);
        title.setText(todo.getTitle());

        if (todo.getDescription().isEmpty()) {
            description.setVisibility(View.GONE);
        } else {
            description.setVisibility(View.VISIBLE);
            description.setText(todo.getDescription());
        }

        return todoView;

    }
}
