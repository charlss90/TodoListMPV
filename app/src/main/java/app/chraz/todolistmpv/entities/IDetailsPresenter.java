package app.chraz.todolistmpv.entities;

import android.widget.EditText;

/**
 * Created by Carlos E. Pazmiño Peralta on 10/3/15.
 */
public interface IDetailsPresenter {
    boolean validateDetails(EditText title, EditText description);
}
