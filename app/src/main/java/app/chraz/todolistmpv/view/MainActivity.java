package app.chraz.todolistmpv.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import app.chraz.todolistmpv.R;
import app.chraz.todolistmpv.entities.IMainView;
import app.chraz.todolistmpv.entities.ITodoMainPresenter;
import app.chraz.todolistmpv.model.Todo;
import app.chraz.todolistmpv.presenter.TodoMainPresenter;
import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Carlos E. Pazmiño Peralta on 08/3/15.
 */
public class MainActivity extends ActionBarActivity implements IMainView{

    @InjectView(R.id.main_layout)
    RelativeLayout mainLayout;
    @InjectView(R.id.todo_list)
    ListView todoList;
    @InjectView(R.id.new_todo_list)
    EditText newTodoList;
    @InjectView(R.id.submenu)
    RelativeLayout submenu;

    private ITodoMainPresenter todoMain;
    private ArrayList<Todo> todos;
    private TodoActionListener newTodoActionListener;
    private TodoAdapter todoAdapter;
    private final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        newTodoActionListener = new TodoActionListener();

        showSubMenu(false);

        todos = new ArrayList<Todo>();

        // Examples --
        todos.add(0, new Todo("Ayudar a Montse", ""));
        todos.add(0, new Todo("Practicar inglés con Montse", ""));
        todos.add(0, new Todo("Ver la televisión",""));
        todoAdapter = new TodoAdapter(this, todos);
        todoList.setAdapter(todoAdapter);

        todoMain = new TodoMainPresenter(this, todoAdapter);

        newTodoList.setOnEditorActionListener(newTodoActionListener);
        newTodoList.setOnFocusChangeListener(newTodoActionListener);
    }

    @Override
    public void clearTodoEditText() {
        newTodoList.setText("");
        mainLayout.requestFocus();
        hideKeyboard();
    }

    @Override
    public void showDetailsView() {
        Intent detailsIntent = new Intent("app.chraz.todolistmpv.TodoDetails");
        detailsIntent.putExtra("title", newTodoList.getText().toString());
        startActivityForResult(detailsIntent, REQUEST_CODE);
    }


    @Override
    public void showSubMenu(boolean hasFocus) {
        if (hasFocus) {
            submenu.setVisibility(View.VISIBLE);
        } else {
            submenu.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private class TodoActionListener implements TextView.OnEditorActionListener, View.OnFocusChangeListener {

        @Override
        public boolean onEditorAction(TextView newTodo, int actionId, KeyEvent event) {
            Boolean isFinish = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                todoMain.addTodo(newTodo.getText().toString(), "");
                isFinish = true;
            }
            return isFinish;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            showSubMenu(hasFocus);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void goToDetails(View v) {
        Intent detailsIntent = new Intent("app.chraz.todolistmpv.TodoDetails");
        detailsIntent.putExtra("title", newTodoList.getText().toString());
        startActivityForResult(detailsIntent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                todoMain.addTodoByIntent(data);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
