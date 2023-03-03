package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button b1;
    Button b2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.toCounterBtn);

        b1 = findViewById(R.id.buttonMessage);
        b2 = findViewById(R.id.buttonToText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, TextActivity.class);
                startActivity(i);
            }

        });
    }

//    public static void main(String []args) {
//        Scanner input = new Scanner(System.in);
//        String userInput;
//        userInput = input.next();
//
//    }


}