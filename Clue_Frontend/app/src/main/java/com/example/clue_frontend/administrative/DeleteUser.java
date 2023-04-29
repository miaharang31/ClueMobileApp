package com.example.clue_frontend.administrative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clue_frontend.R;

public class DeleteUser extends AppCompatActivity {

    Button exit;
    EditText deleteUser;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        exit = findViewById(R.id.exitDelete);
        deleteUser = findViewById(R.id.usernameDelete);
        username = deleteUser.getText().toString();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DeleteUser.this, AdminSettings.class);
                startActivity(intent);
            }
        });
    }
}