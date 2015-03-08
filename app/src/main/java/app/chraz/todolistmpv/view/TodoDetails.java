package app.chraz.todolistmpv.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import app.chraz.todolistmpv.R;

public class TodoDetails extends ActionBarActivity {

    private EditText title;

    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);

        Intent detailsIntent = getIntent();

        title = (EditText) findViewById(R.id.todo_title);
        title.setText(detailsIntent.getStringExtra("title"));
        title.setSelection(title.getText().length());

        description = (EditText) findViewById(R.id.todo_description);
    }

    public void sendDetails(View view) {
        Intent data = new Intent();
        data.putExtra("description", description.getText().toString());
        data.putExtra("title", title.getText().toString());

        setResult(RESULT_OK, data);

        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
