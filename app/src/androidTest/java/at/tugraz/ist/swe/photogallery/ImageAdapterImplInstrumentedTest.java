package at.tugraz.ist.swe.photogallery;

import android.Manifest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.tugraz.ist.swe.photogallery.adapter.ImageAdapter;
import at.tugraz.ist.swe.photogallery.adapter.ImageAdapterFactory;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ImageAdapterImplInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule readRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE);

    @Rule
    public GrantPermissionRule writeRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);


    @Test
    public void initialState() {
        ImageAdapter adapter = ImageAdapterFactory.generateImageAdapter(mainActivityTestRule.getActivity());
        assertNotNull(adapter);
        assertTrue(adapter.getCount() >= 0);
        assertNotEquals(adapter.getItem(1), null);
        assertEquals(adapter.getItemId(1), 1);
    }

}
