package com.example.clue_frontend.administrative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clue_frontend.R;

public class UpgradeUser extends AppCompatActivity {

    Button exit;
    EditText upgradeUser;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_user);

        exit = findViewById(R.id.exitUpgrade);
        upgradeUser = findViewById(R.id.usernameUpgrade);
        username = upgradeUser.getText().toString();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(UpgradeUser.this, AdminSettings.class);
                startActivity(intent);
            }
        });
    }
}