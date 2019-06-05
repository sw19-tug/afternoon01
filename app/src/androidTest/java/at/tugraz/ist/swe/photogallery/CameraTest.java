package at.tugraz.ist.swe.photogallery;

import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.fail;


@RunWith(AndroidJUnit4.class)
public class CameraTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCameraButtonVisible() {
        onView(withId(R.id.camera)).check(matches(isDisplayed()));
    }

    @Test
    public void testCameraAppOpen() throws InterruptedException {
        onView(withId(R.id.camera)).perform(click());
        final ActivityManager activityManager = (ActivityManager) mainActivityTestRule.getActivity().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        Thread.sleep(1000);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        Assert.assertNotNull(procInfos);
        for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
            if (processInfo.processName.equals("com.android.camera")) {
                return;
            }
        }
        fail();
    }
}
