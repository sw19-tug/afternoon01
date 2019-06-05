package at.tugraz.ist.swe.photogallery;

import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ShowPictureActivityTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    @Rule
    public ActivityTestRule<ShowPictureActivity> showPictureRule = new ActivityTestRule<>(ShowPictureActivity.class, true, false);

    @Before
    public void setUp() throws IOException {
        File tempFile = tempFolder.newFile();
        InputStream asset_stream = this.getClass().getClassLoader().getResourceAsStream("test_image.jpg");

        FileOutputStream fos = new FileOutputStream(tempFile);
        copyFile(asset_stream, fos);

        Intent intent = new Intent();
        Uri uri = Uri.parse(tempFile.toString());
        intent.setData(uri);
        showPictureRule.launchActivity(intent);
    }


    private void copyFile(InputStream is, OutputStream os) throws IOException {
        // the size of the buffer doesn't have to be exactly 1024 bytes, try playing around with this number and see what effect it will have on the performance
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }

    }

    @Test
    public void testSelectPicture() {
        assertNotNull(((ImageView) showPictureRule.getActivity().findViewById(R.id.fullscreen_picture)).getDrawable());
    }


    @Test
    public void testShareButtonExists() {
        ImageButton share = showPictureRule.getActivity().findViewById(R.id.button_share);
        assertNotNull(share);
    }

    @Test
    public void testShareButtonEnabled() {
        ImageButton share = showPictureRule.getActivity().findViewById(R.id.button_share);
        assertNotNull(share);
        assertTrue(share.isEnabled());
    }

    @Test
    public void testShareButtonClickable() {
        ImageButton share = showPictureRule.getActivity().findViewById(R.id.button_share);
        assertNotNull(share);
        assertTrue(share.isClickable());
        onView(withId(R.id.fullscreen_picture)).perform(click());
        onView(withId(R.id.button_share)).perform(click());
    }

}