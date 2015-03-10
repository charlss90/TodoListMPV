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
import app.chraz.todolistmpv.entities.IDetailsView;
import app.chraz.todolistmpv.presenter.DetailsPresenter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Carlos E. Pazmiño Peralta on 09/3/15.
 */
public class TodoDetails extends ActionBarActivity implements IDetailsView {

    @InjectView(R.id.todo_title)
    EditText title;

    @InjectView(R.id.todo_description)
    EditText description;

    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        ButterKnife.inject(this);
        detailsPresenter = new DetailsPresenter(this);
        getDetailOfIntent(getIntent());

    }

    @Override
    public void getDetailOfIntent(Intent details) {
        title.setText(details.getStringExtra("title"));
        title.setSelection(title.getText().length());
    }

    @Override
    public void sendDetails(View view) {
        if (detailsPresenter.validateDetails(title, description)) {
            Intent data = new Intent();
            data.putExtra("description", description.getText().toString());
            data.putExtra("title", title.getText().toString());

            setResult(RESULT_OK, data);

            finish();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo_details, menu);
        return true;
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
