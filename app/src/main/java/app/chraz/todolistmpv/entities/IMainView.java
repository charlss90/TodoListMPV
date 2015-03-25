package app.chraz.todolistmpv.entities;

import android.view.View;
import android.widget.ListView;

/**
 * Created by Carlos E. Pazmiño Peralta on 8/03/15.
 */
public interface IMainView {

    void clearTodoEditText();

    void hideKeyboard();

    void showDetailsView();

    void showSubMenu(boolean show);

    void goToDetails(View v);

    ListView getTodoList();
}
