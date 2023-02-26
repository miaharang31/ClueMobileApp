package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clue_frontend.MainActivity;

public class UserSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSignUp.this, MainActivity.class);

                boolean correctFirstName, correctLastName, correctEmail, correctUsername, correctPassword;

                EditText firstName = findViewById(R.id.firstName);
                EditText lastName = findViewById(R.id.lastName);
                EditText email = findViewById(R.id.email);
                EditText username = findViewById(R.id.signUpUsername);
                EditText password = findViewById(R.id.signUpPassword);


                // Turns textbox data into a string without whitespace
                String firstNameData = firstName.getText().toString().trim();
                String lastNameData = lastName.getText().toString().trim();
                String emailData = email.getText().toString().trim();
                String usernameData = username.getText().toString().trim();
                String passwordData = password.getText().toString().trim();

                //checks if the first name textbox is empty and if so, it'll put following error on that textbox.
                //if not empty, the text is correct and the correctFirstName boolean is true.
                if(firstNameData.isEmpty()){
                    firstName.setError("First name cannot be empty");
                    correctFirstName = false;
                }else{
                    firstName.setError(null);
                    correctFirstName = true;
                }

                //checks if the last name textbox is empty and if so, it'll put following error on that textbox.
                //if not empty, the text is correct and the correctLastName boolean is true.
                if(lastNameData.isEmpty()){
                    lastName.setError("Last name cannot be empty");
                    correctLastName = false;
                }else{
                    lastName.setError(null);
                    correctLastName = true;
                }

                //checks if the email textbox is empty and contains an @ symbol. If so, it'll put following error on that textbox.
                //if not empty and contains an @ symbol, the text is correct and the correctEmail boolean is true.

                if(emailData.isEmpty()){
                    email.setError("Email cannot be empty");
                    correctEmail = false;
                }else if(!emailData.contains("@")){
                    email.setError("Email must contain an @ symbol");
                    correctEmail = false;
                }else{
                    email.setError(null);
                    correctEmail = true;
                }

                //checks if the username textbox is empty and has at least 8 characters. If so, it'll put following error on that textbox.
                //if not empty and has at least 8 characters, the text is correct and the correctUsername boolean is true.
                if(usernameData.isEmpty()){
                    username.setError("Username cannot be empty");
                    correctUsername = false;
                }
                else if(usernameData.length() <= 8) {
                    username.setError("Username must have at least eight characters");
                    correctUsername = false;
                }else{
                    username.setError(null);
                    correctUsername = true;
                }

                //checks if the password textbox is empty and has at least 8 characters. If so, it'll put following error on that textbox.
                //if not empty and has at least 8 characters, the text is correct and the correctUsername boolean is true.
                if(passwordData.isEmpty()){
                    password.setError("Password cannot be empty");
                    correctPassword = false;
                }else if(passwordData.length() <= 8) {
                    password.setError("Password must have at least eight characters");
                    correctPassword = false;
                }else{
                    password.setError(null);
                    correctPassword = true;
                }

                // if all textboxes are correct, all data will be added to the SecondActivity (main page for either regular/premium users) and will start
                if (correctFirstName == true && correctLastName == true && correctUsername == true && correctPassword == true && correctEmail == true){
                    intent.putExtra("firstNameData",firstNameData);
                    intent.putExtra("lastNameData",lastNameData);
                    intent.putExtra("emailData",emailData);
                    intent.putExtra("usernameData",usernameData);
                    intent.putExtra("passwordData",passwordData);


                    startActivity(intent);
                }
            }
        });


    }
}