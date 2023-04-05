package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CharacterSelection extends AppCompatActivity {

    Button mustard;
    Button plum;
    Button green;
    Button peacock;
    Button scarlet;
    Button white;
    Button startGame;
    boolean clicked;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        mustard = findViewById(R.id.mustardSelect);
        plum = findViewById(R.id.plumSelect);
        green = findViewById(R.id.greenSelect);
        peacock = findViewById(R.id.peacockSelect);
        scarlet = findViewById(R.id.scarletSelect);
        white = findViewById(R.id.whiteSelect);
        startGame = findViewById(R.id.gameStart);

        mustard.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        plum.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               // plum.isClickable() = false;

            }
        });

        green.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        peacock.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        scarlet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        white.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        startGame.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(CharacterSelection.this, Game.class);
                startActivity(intent);
            }
        });
    }
}