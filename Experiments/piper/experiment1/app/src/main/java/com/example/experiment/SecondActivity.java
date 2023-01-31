package com.example.experiment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);



        TextView counterScreen = (TextView) findViewById(R.id.CounterScreen);
        Button buttonToFirstScreen = findViewById(R.id.toFirstActivityButton);
        Button buttonAdd = findViewById(R.id.AddButton);
        Button buttonSubtract = findViewById(R.id.SubtractButton);


        buttonToFirstScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                counterScreen.setText(Integer.toString(counter));
            }
        });



        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                counter--;
                counterScreen.setText(Integer.toString(counter));
            }
        });
    }
}