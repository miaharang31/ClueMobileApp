package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EndGameWinOrLose extends AppCompatActivity {

    ImageView suspect;
    ImageView weapon;
    ImageView room;

    int suspectCard;
    int weaponCard;
    int roomCard;

    TextView winOrLose;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_win_or_lose);

        suspect = findViewById(R.id.suspect);
        weapon = findViewById(R.id.weapon);
        room = findViewById(R.id.room);
        winOrLose = findViewById(R.id.winLose);


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

        // display this text if player guessed correctly
        winOrLose.setText("Congratulations! You solved the murder!");

        // display this text if player guessed wrong
        winOrLose.setText("\"Oh No!                             You failed to solve the murder.\"");

        // volley stuff
       // RequestQueue queue = Volley.newRequestQueue(EndGameWinOrLose.this);

    }
}