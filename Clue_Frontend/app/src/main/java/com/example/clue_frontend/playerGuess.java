package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

public class playerGuess extends AppCompatActivity {

    TextView exit;

    RadioGroup suspects;
    RadioGroup weapons;
    RadioGroup rooms;

    Button guess;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_guess);

        suspects = findViewById(R.id.suspects);
        weapons = findViewById(R.id.weapons);
        rooms = findViewById(R.id.rooms);

        guess = findViewById(R.id.guess);

        exit = findViewById(R.id.exit);
        final RadioButton[] checkedButton = new RadioButton[3];

        suspects.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // getting radio button from suspects group
                 checkedButton[0] = findViewById(checkedId);
            }
        });

        weapons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // getting radio button from weapons group
                checkedButton[1] = findViewById(checkedId);
            }
        });

        rooms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // getting radio button from rooms group
                checkedButton[2] = findViewById(checkedId);
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(playerGuess.this, CharacterSelection.class);
                startActivity(intent);
            }
        });

        guess.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(suspects.getCheckedRadioButtonId() != -1) {
                    if(weapons.getCheckedRadioButtonId() != -1) {
                        Intent intent = new Intent(playerGuess.this, Game.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(playerGuess.this, "Please choose a weapon.", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(playerGuess.this, "Please choose a suspect.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}