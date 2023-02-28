package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextView newPlayerLink = findViewById(R.id.NewPlayerLink);

//      MIA USED FOR TESTING
//        submitButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, HostLobby.class);
//                startActivity(intent);
//            }
//        });

        //When the sign in button is clicked and data is entered for usename and password
        submitButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            EditText username = findViewById(R.id.loginUsername);
            EditText password = findViewById(R.id.loginPassword);
            String usernameData = username.getText().toString();
            String passwordData = password.getText().toString();
            String firstNameData = " ";
            String lastNameData = " ";
            String emailData = " ";
            boolean checkUsername, checkPassword;

            if(usernameData.isEmpty()){
                username.setError("Username cannot be empty");
                checkUsername = false;
            }else{
                username.setError(null);
                checkUsername = true;
            }

            if(passwordData.isEmpty()){
                password.setError("Password cannot be empty");
                checkPassword = false;
            }else{
                password.setError(null);
                checkPassword = true;
            }

            // if all textboxes are correct, all data will be added to the SecondActivity (main page for either regular/premium users) and will start
            if (checkUsername == true && checkPassword == true){
                intent.putExtra("firstNameData",firstNameData);
                intent.putExtra("lastNameData",lastNameData);
                intent.putExtra("emailData",emailData);
                intent.putExtra("usernameData",usernameData);
                intent.putExtra("passwordData",passwordData);

                startActivity(intent);
            }

        }
    });

        //when the new player link is clicked, go to th SignUpActivity
        newPlayerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserSignUp.class);
                startActivity(intent);
            }
        });
    }
}