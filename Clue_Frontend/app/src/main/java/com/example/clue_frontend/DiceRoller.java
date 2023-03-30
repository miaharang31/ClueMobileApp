package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DiceRoller extends AppCompatActivity {

    ImageView die;
    int numSpaces;
    int delayTime = 20;
    int rollAnimations = 25;
    int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                  R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    Random random = new Random();
    Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);
        die = findViewById(R.id.dice);

        die.setOnClickListener(new View.OnClickListener(){
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
                    numSpaces = random.nextInt(6) + 1;
                    die.setImageResource(dice[numSpaces - 1]);
                    try {
                        // In a try block sleep the thread for a
                        // smooth animation
                        Thread.sleep(delayTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // Define a Thread object and pass the runnable object
        // in the constructor.
        Thread thread = new Thread(runnable);
        // Start the thread. This will cause the run() method to be called
        // where all the dice rolling animation happens.
        thread.start();

  }
}