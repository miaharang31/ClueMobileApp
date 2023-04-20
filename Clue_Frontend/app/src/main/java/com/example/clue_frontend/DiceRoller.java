package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DiceRoller extends AppCompatActivity {

    public static int numSpaces;
    ImageView die1;
    ImageView die2;
    TextView direction;
    int die1Number;
    int die2Number;
    int delayTime = 20;
    int rollAnimations = 25;
    int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                  R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    Random random = new Random();
    Timer timer = new Timer();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);
        die1 = findViewById(R.id.die1);
        die2 = findViewById(R.id.die2);
        direction = findViewById(R.id.textView4);

        die1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        die2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        direction.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        die1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                rollDice();
                timer.schedule(new TimerTask(){
                    @Override
                    public void run() {
                        Intent intent = new Intent(DiceRoller.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        die2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                rollDice();
                timer.schedule(new TimerTask(){
                    @Override
                    public void run() {
                        Intent intent = new Intent(DiceRoller.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
    }
    private void rollDice() {
        // Define a Runnable object
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // In the run() method, use a for loop to iterate
                // some code to show rolling dice animation
                for (int i = 0; i < rollAnimations; i++) {
                    die1Number = random.nextInt(6) + 1;
                    die1.setImageResource(dice[die1Number - 1]);
                    die2Number = random.nextInt(6) + 1;
                    die2.setImageResource(dice[die2Number - 1]);
                    try {
                        Thread.sleep(delayTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                numSpaces = die1Number + die2Number;
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

  }
}