package at.tugraz.ist.swe.photogallery;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ShowPictureEspressoTest {
    @Rule
    public ActivityTestRule<ShowPicture> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void testButtonsVisible() {
        onView(withId(R.id.fullscreen_picture)).check(matches(isDisplayed()));
    }
}
