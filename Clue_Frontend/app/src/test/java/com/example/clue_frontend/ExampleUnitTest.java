package com.example.clue_frontend;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.*;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {


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
    public void reverseDefaultString(){
        String testString = "defaultstring";
        String resultString = "gnirtstluafed";

        activityScenarioRule.getScenario().onActivity(activity -> {
            activity.defaultString = testString;
            activity.aSwitch.setChecked(true);
        });

        onView(withId(R.id.submit)).perform(click());
        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {}

        // Verify that volley returned the correct value
        onView(withId(R.id.myTextView)).check(matches(withText(endsWith(resultString))));
    }


}