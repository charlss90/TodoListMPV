package app.chraz.todolistmpv.entities;

import android.content.Intent;
import android.view.View;

/**
 * Created by Carlos E. Pazmiño Peralta on 10/3/15.
 */
public interface IDetailsView {

    void getDetailOfIntent(Intent details);

    void sendDetails(View v);
}
