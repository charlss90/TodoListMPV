package app.chraz.todolistmpv.presenter;

import android.text.TextUtils;
import android.widget.EditText;

import app.chraz.todolistmpv.entities.IDetailsPresenter;
import app.chraz.todolistmpv.entities.IDetailsView;

/**
 * Created by Carlos E. Pazmiño Peralta on 10/3/15.
 */
public class DetailsPresenter implements IDetailsPresenter {

    private IDetailsView detailsView;

    public DetailsPresenter(IDetailsView detailsView) {
        this.detailsView = detailsView;
    }

    @Override
    public boolean validateDetails(EditText title, EditText description) {
        boolean isValidate = false;

        if(!TextUtils.isEmpty(title.getText()) &&
                !TextUtils.isEmpty(description.getText()))
            isValidate = true;

        return isValidate;
    }
}
