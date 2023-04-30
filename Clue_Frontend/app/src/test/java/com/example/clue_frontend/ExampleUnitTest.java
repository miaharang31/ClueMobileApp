package com.example.clue_frontend;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void checkLoginData() {

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

}