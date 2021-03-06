package at.tugraz.ist.swe.photogallery;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.tugraz.ist.swe.photogallery.adapter.ImageAdapter;
import at.tugraz.ist.swe.photogallery.adapter.ImageAdapterFactory;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.anything;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ShowPictureIntentTest {
    @Rule
    public IntentsTestRule<MainActivity> selectPictureRule = new IntentsTestRule<>(MainActivity.class, true, true );

    @Test
    public void testSelectPicture() {
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new Intent());
        intending(hasComponent(hasShortClassName(".ShowPicture"))).respondWith(result);
        ImageAdapter imageAdapter = ImageAdapterFactory.generateImageAdapter(selectPictureRule.getActivity());
        assertNotNull(imageAdapter);
        assertTrue(imageAdapter.getCount() > 0);
        assertNotNull(imageAdapter.getItem(1));
        onData(anything()).inAdapterView(withId(R.id.gallery)).atPosition(0).perform(click());
        intended(hasComponent(ShowPictureActivity.class.getName()));
    }

}