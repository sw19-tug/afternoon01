package at.tugraz.ist.swe.photogallery;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Locale;

import static at.tugraz.ist.swe.photogallery.BasicGalleryInstrumentedTest.assertViewWithTextIsVisible;

public class BasicGalleryInstrumentedAllowPermissionTest {
    UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    public static String ALLOW;
    public static String DENY;


    @BeforeClass
    public static void beforeClass()
    {
        if(Locale.getDefault() == Locale.GERMAN)
        {
            ALLOW = "ZULASSEN";
            DENY = "ABLEHNEN";
        }
        else
        {
            ALLOW = "ALLOW";
            DENY =  "DENY";
        }

    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    public static void denyCurrentPermission(UiDevice device) throws UiObjectNotFoundException {
        UiObject denyButton = device.findObject(new UiSelector().text(DENY));
        denyButton.click();
    }

    public static void allowCurrentPermission(UiDevice device) throws UiObjectNotFoundException {
        UiObject allowButton = device.findObject(new UiSelector().text(ALLOW));
        allowButton.click();
    }

    @Test
    public void shouldDisplayPermissionRequestDialogAtStartup() throws Exception {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertViewWithTextIsVisible(device, ALLOW);
        assertViewWithTextIsVisible(device, DENY);

        // cleanup for the next test
        denyCurrentPermission(device);
    }

    @Test
    public void shouldDisplayPermissionRequestDialogOnDeny() throws Exception {
        assertViewWithTextIsVisible(device, DENY);

        // cleanup for the next test
        denyCurrentPermission(device);
        assertViewWithTextIsVisible(device, ALLOW);
        assertViewWithTextIsVisible(device, DENY);
        denyCurrentPermission(device);
    }

}
