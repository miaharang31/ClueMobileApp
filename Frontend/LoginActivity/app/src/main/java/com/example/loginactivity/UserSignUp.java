package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSignUp.this, SecondActivity.class);

                Log.d("Sign Up Screen", "On Sign Up Screen");

                EditText firstName = findViewById(R.id.firstName);
                EditText lastName = findViewById(R.id.lastName);
                EditText username = findViewById(R.id.signUpUsername);
                EditText password = findViewById(R.id.signUpPassword);

                String firstNameData = firstName.getText().toString();
                String lastNameData = lastName.getText().toString();
                String usernameData = username.getText().toString();
                String passwordData = password.getText().toString();

                intent.putExtra("firstNameData",firstNameData);
                intent.putExtra("lastNameData",lastNameData);
                intent.putExtra("usernameData",usernameData);
                intent.putExtra("passwordData",passwordData);


                Log.d("firstNameOutput", "firstnameData: " + firstNameData);
                Log.d("lastNameOutput", "lastnameData: " + firstNameData);
                Log.d("usernameOutput", "usernameData: " + usernameData);
                Log.d("passwordOutput", "passwordData: " + passwordData);
                startActivity(intent);

            }
        });
    }
}