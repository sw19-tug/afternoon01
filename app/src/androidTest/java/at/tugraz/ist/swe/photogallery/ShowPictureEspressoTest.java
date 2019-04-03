package at.tugraz.ist.swe.photogallery;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.anything;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ShowPictureEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> selectPictureRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Test
    public void testSelectPicture() {
        ImageAdapter imageAdapter = new ImageAdapter(selectPictureRule.getActivity());
        assertNotNull(imageAdapter);
        assertTrue(imageAdapter.getCount() > 0);
        assertNotNull(imageAdapter.getItem(1));
        onData(anything()).inAdapterView(withId(R.id.gallery)).atPosition(0).perform(click());
        intended(hasComponent(ShowPicture.class.getName()));
    }

}