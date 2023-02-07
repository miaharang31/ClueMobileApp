package com.example.experiment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    b1 = (Button) findViewById(R.id.buttonMessage);
    b2 = (Button) findViewById(R.id.buttonNext);

    b1.setOnClickListener(new View.onClickListener()){

    }


}