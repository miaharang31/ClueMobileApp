package com.example.premium;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PremiumLobbyActivity extends AppCompatActivity {

    Button players3;
    Button players4;
    Button players5;
    Button players6;
    Button lobby;
    int numPlayers = 0;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiumlobby);

        players3 = findViewById(R.id.players5button);
        players4 = findViewById(R.id.players4button);
        players5 = findViewById(R.id.players5button);
        players6 = findViewById(R.id.players6button);
        lobby = findViewById(R.id.LobbyButton);


        players3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numPlayers = 3;
                Toast.makeText(getApplicationContext(), numPlayers , Toast.LENGTH_LONG).show();
            }
        });

        players4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numPlayers = 4;
                Toast.makeText(getApplicationContext(), numPlayers, Toast.LENGTH_LONG).show();
            }
        });

        players5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numPlayers = 5;
                Toast.makeText(getApplicationContext(), numPlayers, Toast.LENGTH_LONG).show();
            }
        });

        players6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numPlayers = 6;
                Toast.makeText(getApplicationContext(), numPlayers, Toast.LENGTH_LONG).show();
            }
        });

        lobby.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent i = new Intent(PremiumLobbyActivity.this, LobbyActivity.class);
                startActivity(i);
            }
        });

    }

}
