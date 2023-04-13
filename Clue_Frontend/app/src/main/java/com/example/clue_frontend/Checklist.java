package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.clue_frontend.GamePlay.playerGuess;

public class Checklist extends AppCompatActivity {

    CheckBox mustard;
    Button plum;
    Button green;
    Button peacock;
    Button scarlet;
    Button white;

    Button knife;
    Button candlestick;
    Button revolver;
    Button rope;
    Button leadPipe;
    Button wrench;

    Button hall;
    Button lounge;
    Button diningRoom;
    Button kitchen;
    Button ballroom;
    Button conservatory;
    Button billiardRoom;
    Button library;
    Button study;

    TextView exit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        mustard = findViewById(R.id.mustard);
        plum = findViewById(R.id.plum);
        green = findViewById(R.id.green);
        peacock = findViewById(R.id.peacock);
        scarlet = findViewById(R.id.scarlet);
        white = findViewById(R.id.white);

        knife = findViewById(R.id.knife);
        candlestick = findViewById(R.id.candlestick);
        revolver = findViewById(R.id.revolver);
        rope = findViewById(R.id.rope);
        leadPipe = findViewById(R.id.pipe);
        wrench = findViewById(R.id.wrench);

        hall = findViewById(R.id.hall);
        lounge = findViewById(R.id.lounge);
        diningRoom = findViewById(R.id.dining);
        kitchen = findViewById(R.id.kitchen);
        ballroom = findViewById(R.id.ballroom);
        conservatory = findViewById(R.id.conservatory);
        billiardRoom = findViewById(R.id.billiard);
        library = findViewById(R.id.library);
        study = findViewById(R.id.study);

        exit = findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Checklist.this, playerGuess.class);
                startActivity(intent);
            }
        });
    }
}