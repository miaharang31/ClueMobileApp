package com.example.clue_frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    private static final int SIMULATED_DELAY_MS = 500;

    @Rule   // needed to launch the activity
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void capitalizeString() {
        String testString = "name";
        String resultString = "name";
        // Type in testString and send request
        onView(withId(R.id.firstName)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.signUpButton)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }
        //onView(withId(R.id.text_simple)).check(matches(withText("Hello Espresso!")));

        // Verify that volley returned the correct value
        onView(withId(R.id.signUpButton)).check(matches(withText(resultString)));

    }
}