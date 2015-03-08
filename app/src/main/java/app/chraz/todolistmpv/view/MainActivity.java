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
import app.chraz.todolistmpv.model.Todo;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout mainLayout;
    private ListView todoList;
    private EditText newTodoList;
    private RelativeLayout submenu;
    private ArrayList<Todo> todos;
    private TodoActionListener newTodoActionListener;
    private final int REQUEST_CODE = 1;


    private void hideKeyboard() {
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
                todos.add(0, new Todo(newTodo.getText().toString(), ""));
                ((BaseAdapter) todoList.getAdapter()).notifyDataSetChanged();
                hideKeyboard();

                newTodo.setText("");
                mainLayout.requestFocus();

                isFinish = true;
            }
            return isFinish;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                submenu.setVisibility(View.VISIBLE);
            } else {
                submenu.setVisibility(View.GONE);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        todoList = (ListView) findViewById(R.id.todo_list);
        newTodoList = (EditText) findViewById(R.id.new_todo_list);
        submenu = (RelativeLayout) findViewById(R.id.submenu);
        newTodoActionListener = new TodoActionListener();


        submenu.setVisibility(View.GONE);

        todos = new ArrayList<Todo>();

        todos.add(0, new Todo("Ayudar a Montse", ""));
        todos.add(0, new Todo("Practicar inglés con Montse", ""));
        todos.add(0, new Todo("Ver la televisión",""));

        todoList.setAdapter(new TodoAdapter(this, todos));

        newTodoList.setOnEditorActionListener(newTodoActionListener);
        newTodoList.setOnFocusChangeListener(newTodoActionListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startDetails(View v) {
        Intent detailsIntent = new Intent("app.chraz.todolistmpv.TodoDetails");
        detailsIntent.putExtra("title", newTodoList.getText().toString());
        startActivityForResult(detailsIntent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                newTodoList.setText("");
                mainLayout.requestFocus();

                String responseTitle = data.getStringExtra("title");
                String responseDescription = data.getStringExtra("description");

                todos.add(0, new Todo(responseTitle, responseDescription));
                ((BaseAdapter) todoList.getAdapter()).notifyDataSetChanged();
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
