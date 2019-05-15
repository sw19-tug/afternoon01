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
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import java.util.Arrays;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ImageSortInstrumentedTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSpinnerVisible() {
        onView(withId(R.id.spinner_toolBar)).check(matches(isDisplayed()));
    }

    @Test
    public void checkSpinnerDate(){
        onView(withId(R.id.spinner_toolBar)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Date"))).perform(click());
        onView(withId(R.id.spinner_toolBar)).check(matches(withSpinnerText(containsString("Date"))));
    }

    @Test
    public void checkSpinnerName(){
        onView(withId(R.id.spinner_toolBar)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Name"))).perform(click());
        onView(withId(R.id.spinner_toolBar)).check(matches(withSpinnerText(containsString("Name"))));
    }

    @Test
    public void checkSpinnerSize(){
        onView(withId(R.id.spinner_toolBar)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Size"))).perform(click());
        onView(withId(R.id.spinner_toolBar)).check(matches(withSpinnerText(containsString("Size"))));
    }


    @Test
    public void checkSpinnerSizeAvailable(){
        Arrays.asList(mainActivityTestRule.getActivity().getResources().getStringArray(R.array.dropdown_sort)).contains("Size");
    }

}
