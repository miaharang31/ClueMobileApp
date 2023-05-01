package com.example.clue_frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Activity;
import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.StringEndsWith.endsWith;
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
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.clue_frontend", appContext.getPackageName());
    }

    @Test
    public void checkLoginData() {

        String firstName = "firstNameTest";
        String lastName = "lastNameTest";
        String email = "emailTest";
        String username = "usernameTest";
        String password = "passwordTest";;

        assertNotEquals(null, firstName);
        assertNotEquals(null, lastName);
        assertNotEquals(null, email);
        assertNotEquals(null, username);
        assertNotEquals(null, password);


    }

    @Test
    public void checkCharacterSelection() {

        String firstName = UserSignUp.firstNameData;
        String lastName = UserSignUp.lastNameData;
        String email = UserSignUp.emailData;
        String username = UserSignUp.usernameData;
        String password = UserSignUp.passwordData;

        assertNotEquals(null, firstName);
        assertNotEquals(null, lastName);
        assertNotEquals(null, email);
        assertNotEquals(null, username);
        assertNotEquals(null, password);


    }

    @Test
    public void reverseString(){
        String testString = "hello";
        String resultString = "olleh";


        // Type in testString and send request
        //onView(withId(R.id.stringEntry)).perform(typeText(testString), closeSoftKeyboard());
        //onView(withId(R.id.submit)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        // Verify that volley returned the correct value

        //onView(withId(R.id.myTextView)).check(matches(withText(endsWith(resultString))));


        //onView(withId(R.id.myTextView)).check(matches(withText(endsWith(testString))));

    }


}