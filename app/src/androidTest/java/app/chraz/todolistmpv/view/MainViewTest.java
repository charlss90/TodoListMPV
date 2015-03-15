package app.chraz.todolistmpv.view;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import app.chraz.todolistmpv.R;

@RunWith(RobolectricTestRunner.class)
public class MainViewTest  {

    private MainActivity mainActivity;

    @Before
    public void setup() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void shouldHaveApplicationName() throws Exception {
        String appName = new MainActivity().getResources().getString(R.string.app_name);
        Assert.assertNotNull(mainActivity);
    }
}
