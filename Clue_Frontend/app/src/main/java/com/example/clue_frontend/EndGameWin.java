package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class EndGameWin extends AppCompatActivity {

    ImageView suspect;
    ImageView weapon;
    ImageView room;

    int suspectCard;
    int weaponCard;
    int roomCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_win);

        suspect = findViewById(R.id.suspect);
        weapon = findViewById(R.id.weapon);
        room = findViewById(R.id.room);


        // randomly chosen suspect card players had to guess
        suspectCard = R.drawable.scarlet_card;


        // randomly chosen weapon card players had to guess
        weaponCard = R.drawable.revolver_card;



        // randomly chosen room card players had to guess
        roomCard = R.drawable.conservatory_card;

        // image views displaying the correct cards 
        suspect.setImageResource(suspectCard);
        weapon.setImageResource(weaponCard);
        room.setImageResource(roomCard);

    }
}