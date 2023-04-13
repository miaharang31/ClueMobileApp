package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class EndGameLose extends AppCompatActivity {

    ImageView suspect;
    ImageView weapon;
    ImageView room;

    int suspectCard;
    int weaponCard;
    int roomCard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_lose);

        // TODO display correct cards

        suspect = findViewById(R.id.suspect);
        weapon = findViewById(R.id.weapon);
        room = findViewById(R.id.room);


        // set to
        suspectCard = R.drawable.scarlet_card;



        weaponCard = R.drawable.revolver_card;
        roomCard = R.drawable.conservatory_card;

        suspect.setImageResource(suspectCard);
        weapon.setImageResource(weaponCard);
        room.setImageResource(roomCard);

    }
}