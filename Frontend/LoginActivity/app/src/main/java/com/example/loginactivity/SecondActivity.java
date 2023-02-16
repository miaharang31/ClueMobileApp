package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
// NOTE: This activity is just to prove that the data entered from either the MainActivity or the SignUp Activity is correct.
//       This will not be in the final product
public class SecondActivity extends AppCompatActivity {

    TextView usernameDisplay;
    TextView passwordDisplay;

    TextView emailDisplay;
    TextView firstNameDisplay;
    TextView lastNameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        //adds a label to the data
        String firstName = "FirstName: " + intent.getStringExtra("firstNameData");
        String lastName = "LastName: " + intent.getStringExtra("lastNameData");
        String email = "Email: " + intent.getStringExtra("emailData");
        String username = "Username: " + intent.getStringExtra("usernameData");
        String password = "Password: " + intent.getStringExtra("passwordData");

        firstNameDisplay = (TextView) findViewById(R.id.firstNameDisplay);
        lastNameDisplay = (TextView) findViewById(R.id.lastNameDisplay);
        emailDisplay = (TextView) findViewById(R.id.emailDisplay);
        usernameDisplay = (TextView) findViewById(R.id.usernameDisplay);
        passwordDisplay = (TextView) findViewById(R.id.passwordDisplay);

        //Shows data from either MainActivity or SignUp Activity
        firstNameDisplay.setText(firstName);
        lastNameDisplay.setText(lastName);
        emailDisplay.setText(email);
        usernameDisplay.setText(username);
        passwordDisplay.setText(password);

    }
}