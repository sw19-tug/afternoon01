package at.tugraz.ist.swe.photogallery;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BasicGalleryLandscapeInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkLandscapeOrientation() throws InterruptedException {

        //int orientation = mainActivityActivityTestRule.getActivity().getResources().getConfiguration().orientation;
        mainActivityActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Thread.sleep(500);
        assertTrue(mainActivityActivityTestRule.getActivity().getRequestedOrientation() == Configuration.ORIENTATION_LANDSCAPE);
        /*
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
        } else {
            // In portrait
        }
        */
    }
}
