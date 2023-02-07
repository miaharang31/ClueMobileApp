package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView usernameDisplay;
    TextView passwordDisplay;
    TextView firstNameDisplay;
    TextView lastNameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Log.d("Second Screen", "On Second Screen");

        String firstName = "FirstName: " + intent.getStringExtra("firstNameData");
        String lastName = "LastName: " + intent.getStringExtra("lastNameData");
        String username = "Username: " + intent.getStringExtra("usernameData");
        String password = "Password: " + intent.getStringExtra("passwordData");

        firstNameDisplay = (TextView) findViewById(R.id.firstNameDisplay);
        lastNameDisplay = (TextView) findViewById(R.id.lastNameDisplay);
        usernameDisplay = (TextView) findViewById(R.id.usernameDisplay);
        passwordDisplay = (TextView) findViewById(R.id.passwordDisplay);

        firstNameDisplay.setText(firstName);
        lastNameDisplay.setText(lastName);
        usernameDisplay.setText(username);
        passwordDisplay.setText(password);

    }
}