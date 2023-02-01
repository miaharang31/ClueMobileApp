package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView usernameDisplay;
    TextView passwordDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String username = "Username: " + intent.getStringExtra("usernameData");
        String password = "Password: " + intent.getStringExtra("passwordData");

        usernameDisplay = (TextView) findViewById(R.id.usernameDisplay);
        passwordDisplay = (TextView) findViewById(R.id.passwordDisplay);
        usernameDisplay.setText(username);
        passwordDisplay.setText(password);

    }
}