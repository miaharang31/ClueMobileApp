package com.example.premium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PremiumLobbyActivity extends AppCompatActivity {

    Button players3;
    Button players4;
    Button players5;
    Button players6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiumlobby);

        players3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(PremiumLobbyActivity.this, PremiumActivity.class);
                startActivity(i);
            }
        });

        players4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(PremiumLobbyActivity.this, PremiumActivity.class);
                startActivity(i);
            }
        });

        players5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(PremiumLobbyActivity.this, PremiumActivity.class);
                startActivity(i);
            }
        });

        players6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(PremiumLobbyActivity.this, PremiumActivity.class);
                startActivity(i);
            }
        });

    }

}
