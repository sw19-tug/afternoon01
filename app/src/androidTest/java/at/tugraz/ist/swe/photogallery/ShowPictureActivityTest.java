package at.tugraz.ist.swe.photogallery;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
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
    public TemporaryFolder tempFolder = new TemporaryFolder();
    @Rule
    public ActivityTestRule<ShowPictureActivity> showPictureRule = new ActivityTestRule<>(ShowPictureActivity.class, true, false);

    @Test
    public void testSelectPicture() throws IOException {
        File tempFile = tempFolder.newFile();
        InputStream asset_stream = this.getClass().getClassLoader().getResourceAsStream("test_image.jpg");
        Files.copy(asset_stream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        Intent intent = new Intent();
        Uri uri = Uri.parse(tempFile.toPath().toString());
        intent.setData(uri);
        showPictureRule.launchActivity(intent);

        ((ImageView)showPictureRule.getActivity().findViewById(R.id.fullscreen_picture)).getDrawable().isVisible();

    }

}