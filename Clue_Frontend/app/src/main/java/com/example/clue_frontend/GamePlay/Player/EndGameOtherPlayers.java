package com.example.clue_frontend.GamePlay.Player;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clue_frontend.R;

public class EndGameOtherPlayers extends AppCompatActivity {

    ImageView suspect;
    ImageView weapon;
    ImageView room;

    int suspectCard;
    int weaponCard;
    int roomCard;

    TextView playerName;
    TextView winOrLose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_other_players);

        suspect = findViewById(R.id.suspect);
        weapon = findViewById(R.id.weapon);
        room = findViewById(R.id.room);
        playerName = findViewById(R.id.playerName);
        winOrLose = findViewById(R.id.winOrLose);


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

        playerName.setText("name");

        // display this text if player guessed correctly
        winOrLose.setText("Solved the murder!");

        // display this text if player guessed wrong
        winOrLose.setText("Guessed wrong!");
    }
}