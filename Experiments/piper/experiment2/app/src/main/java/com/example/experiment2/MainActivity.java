package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                EditText username = findViewById(R.id.loginUsername);
                String usernameData = username.getText().toString();
                EditText password = findViewById(R.id.loginPassword);
                String passwordData = password.getText().toString();

                intent.putExtra("usernameData",usernameData);
                intent.putExtra("passwordData",passwordData);
                startActivity(intent);

            }
        });
    }
}