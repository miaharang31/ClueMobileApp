package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clue_frontend.databinding.CardLayoutBinding;

public class CardLayout extends AppCompatActivity {


    private CardLayoutBinding binding;

    public void exitName(View v) {
        startActivity(new Intent(CardLayout.this, CardButton.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CardLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        btn = (Button) findViewById(R.id.exitbtn);
        int[] images = {R.drawable.green, R.drawable.mustard_card, R.drawable.peacock, R.drawable.plum, R.drawable.scarlet_card, R.drawable.white, R.drawable.candlestick_card,R.drawable.knife_card,R.drawable.lead_pipe_card,R.drawable.revolver_card,R.drawable.rope_card,R.drawable.wrench_card,R.drawable.ballroom_card,R.drawable.billiard_card,R.drawable.conservatory_card,R.drawable.dining_card,R.drawable.hall_card,R.drawable.kitchen_card,R.drawable.library_card,R.drawable.lounge_card,R.drawable.study_card};
        String[] names = {"Mr. Green", "Colonel Mustard", "Mrs. Peacock", "Professor Plum", "Miss Scarlet", "Mrs. White", "the candlestick", "the knife", "the lead pipe", "the revolver", "the rope", "the wrench", "the ballroom", "the billiard room", "the conservatory", "the dining room", "the hall", "the kithcen", "the library", "the lounge", "the study"};
        GridAdapter gridAdapter = new GridAdapter(CardLayout.this, images);
        binding.gridViewCards.setAdapter(gridAdapter);

        binding.gridViewCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CardLayout.this,"Do you want to show " + names[position] + " to the person who's turn it is?", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
