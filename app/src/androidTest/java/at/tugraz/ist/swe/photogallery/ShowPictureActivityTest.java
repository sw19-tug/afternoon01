package at.tugraz.ist.swe.photogallery;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.anything;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ShowPictureActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> showPictureRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Test
    public void testSelectPicture() {
      ImageAdapter imageAdapter = new ImageAdapter(showPictureRule.getActivity());
      assertNotNull(imageAdapter);
      assertTrue(imageAdapter.getCount() > 0);
      assertNotNull(imageAdapter.getItem(1));

      Intent intent = new Intent();
      intent.putExtra("picture_uri", imageAdapter.getItemUri(1));
      showPictureRule.launchActivity(intent);
    }

}