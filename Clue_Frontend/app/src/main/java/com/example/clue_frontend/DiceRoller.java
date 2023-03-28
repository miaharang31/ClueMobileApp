package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class DiceRoller extends AppCompatActivity {

    public static ImageView die;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);

        final int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

        die = findViewById(R.id.dice);

        die.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Random random = new Random();
                int num = random.nextInt(6);
                die.setImageResource(dice[num]);
            }
        });
    }
}