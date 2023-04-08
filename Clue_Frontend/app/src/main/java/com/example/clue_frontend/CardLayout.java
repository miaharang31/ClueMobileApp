package com.example.clue_frontend;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CardLayout extends AppCompatActivity {
    @Override
    protected void onCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_grid_layout);
    }
}
