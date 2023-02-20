package com.example.premium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button premium;
    Button standard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        standard = findViewById(R.id.StandardButton);
        premium = findViewById(R.id.PremiumButton);



    }
}