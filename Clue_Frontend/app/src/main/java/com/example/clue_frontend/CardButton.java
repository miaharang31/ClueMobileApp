package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CardButton extends AppCompatActivity {
    ImageView iv;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_cards_layout);

        iv = findViewById(R.id.open_checklist);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardButton.this, playerGuess.class);
                startActivity(intent);
            }
        });

        imageView = findViewById(R.id.open_cards);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardButton.this, CardLayout.class);
                startActivity(intent);
            }
        });
    }
}
