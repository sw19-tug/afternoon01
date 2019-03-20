package at.tugraz.ist.swe.photogallery;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.tugraz.ist.swe.photogallery.ImageAdapter;

import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ImageAdapterInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void initialState() {
        ImageAdapter adapter = new ImageAdapter(mainActivityTestRule.getActivity());
        assertNotNull(adapter);
        assertEquals(adapter.getCount(), 0);
        assertEquals(adapter.GetItem(0), null);
        assertEquals(adapter.GetItemId(0), 0);
        assertNotNull(adapter.getView(0, null, null);
    }


}
