package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextView newPlayerLink = findViewById(R.id.NewPlayerLink);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                Log.d("Sign In Screen", "On Sign In Screen");

                EditText username = findViewById(R.id.loginUsername);
                EditText password = findViewById(R.id.loginPassword);
                String usernameData = username.getText().toString();
                String passwordData = password.getText().toString();
                String firstNameData = " ";
                String lastNameData = " ";

                intent.putExtra("usernameData",usernameData);
                intent.putExtra("passwordData",passwordData);
                intent.putExtra("firstNameData",firstNameData);
                intent.putExtra("lastNameData",lastNameData);
                startActivity(intent);

            }
        });


        newPlayerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserSignUp.class);
                startActivity(intent);
            }
        });
    }
}