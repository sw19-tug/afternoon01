package at.tugraz.ist.swe.photogallery;

import android.provider.MediaStore;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CameraIntentTest {
    @Rule
    public IntentsTestRule<MainActivity> selectPictureRule = new IntentsTestRule<>(MainActivity.class, true, true );

    @Test
    public void testSelectPicture() {
        onView(withId(R.id.camera)).perform(click());
        intended(hasAction(MediaStore.ACTION_IMAGE_CAPTURE));
    }
}