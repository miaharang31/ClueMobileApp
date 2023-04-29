package com.example.clue_frontend.administrative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clue_frontend.R;

public class Settings extends AppCompatActivity {

    Button showUsers;
    Button showLobbies;
    Button upgradeUser;
    Button viewCards;
    Button deleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        showUsers = findViewById(R.id.showUsers);
        showLobbies = findViewById(R.id.showLobbies);
        upgradeUser = findViewById(R.id.upgradeUser);
        viewCards = findViewById(R.id.viewCards);
        deleteUser = findViewById(R.id.deleteUser);

        showUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Settings.this, ShowUsers.class);
                startActivity(intent);
            }
        });

        showLobbies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Settings.this, ShowLobbies.class);
                startActivity(intent);
            }
        });

        upgradeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Settings.this, UpgradeUser.class);
                startActivity(intent);
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Settings.this, DeleteUser.class);
                startActivity(intent);
            }
        });

        viewCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Settings.this, ViewCards.class);
                startActivity(intent);
            }
        });


    }
}