
package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clue_frontend.GamePlay.Game;

public class CharacterSelection extends AppCompatActivity {

    Button mustard;
    Button plum;
    Button green;
    Button peacock;
    Button scarlet;
    Button white;
    Button startGame;
    int totalPlayers;
    int numPlayers;



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
                mustard.setClickable(false);
                clicked(mustard);
                numPlayers++;

            }
        });

        plum.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               plum.setClickable(false);
               clicked(plum);
               numPlayers++;

            }
        });

        green.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                green.setClickable(false);
                clicked(green);
                numPlayers++;

            }
        });

        peacock.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                peacock.setClickable(false);
                clicked(peacock);
                numPlayers++;

            }
        });

        scarlet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                scarlet.setClickable(false);
                clicked(scarlet);
                numPlayers++;
            }
        });

        white.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clicked(white);
                numPlayers++;
            }
        });

        startGame.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(numPlayers == totalPlayers) {
                    Intent intent = new Intent(CharacterSelection.this, Game.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void clicked(Button characterName) {
        characterName.setClickable(false);
        characterName.setText(null);
        characterName.setBackgroundColor(0x8A8787);
    }


}



