package com.example.gameplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {

    Button mustard;
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

//    Button hall;
//    Button lounge;
//    Button diningRoom;
//    Button kitchen;
//    Button ballroom;
//    Button conservatory;
//    Button billiardRoom;
//    Button library;
//    Button study;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

//        hall = findViewById(R.id.hall);
//        lounge = findViewById(R.id.lounge);
//        diningRoom = findViewById(R.id.dining);
//        kitchen = findViewById(R.id.kitchen);
//        ballroom = findViewById(R.id.ballroom);
//        conservatory = findViewById(R.id.conservatory);
//        billiardRoom = findViewById(R.id.billiard);
//        library = findViewById(R.id.library);
//        study = findViewById(R.id.study);

    }
}