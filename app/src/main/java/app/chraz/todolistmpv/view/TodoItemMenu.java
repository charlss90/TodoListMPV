package app.chraz.todolistmpv.view;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import app.chraz.todolistmpv.R;
import app.chraz.todolistmpv.entities.ITodoMainPresenter;

/**
 * Created by chraz on 25/3/15.
 */
public class TodoItemMenu extends PopupMenu implements PopupMenu.OnMenuItemClickListener {

//    private static final String  NAME_CLASS = getClass().getName();

    Menu menu;
    MenuItem deleteItem;
    ITodoMainPresenter todoMainPresenter;

    public TodoItemMenu(Context context, View view, ITodoMainPresenter todoMainPresenter) {
        super(context, view);
        this.todoMainPresenter = todoMainPresenter;
        menu = getMenu();

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_todo_item, menu);

        deleteItem = menu.findItem(R.id.delete);

        setOnMenuItemClickListener(this);
    }

    public void setViewDeleteAction(View todoView) {
        if (deleteItem != null)
            deleteItem.setActionView(todoView);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete:
                int position = todoMainPresenter.getIndexOfTodoListByView(menuItem.getActionView());
                todoMainPresenter.removeTodoByPosition(position);
                break;
            default:
                Log.d("TodoItemMenu", "Default");
                break;
        }
        return false;
    }
}
