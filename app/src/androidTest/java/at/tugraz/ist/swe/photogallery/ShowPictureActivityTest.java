package at.tugraz.ist.swe.photogallery;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.net.Uri;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.ImageView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


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
    public TemporaryFolder tempFolder = new TemporaryFolder();
    @Rule
    public ActivityTestRule<ShowPictureActivity> showPictureRule = new ActivityTestRule<>(ShowPictureActivity.class, true, false);

    @Test
    public void testSelectPicture() throws IOException {
        File tempFile = tempFolder.newFile();
        InputStream asset_stream = this.getClass().getClassLoader().getResourceAsStream("test_image.jpg");

        FileOutputStream fos = new FileOutputStream(tempFile);
        copyFile(asset_stream, fos);

        Intent intent = new Intent();
        Uri uri = Uri.parse(tempFile.toString());
        intent.setData(uri);
        showPictureRule.launchActivity(intent);

        ((ImageView)showPictureRule.getActivity().findViewById(R.id.fullscreen_picture)).getDrawable().isVisible();

    }

    private void copyFile(InputStream is, OutputStream os) throws IOException {
        // the size of the buffer doesn't have to be exactly 1024 bytes, try playing around with this number and see what effect it will have on the performance
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }

    }

}