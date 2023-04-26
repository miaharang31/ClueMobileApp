package com.example.clue_frontend;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_page);
        TextView objective = (TextView) findViewById(R.id.rulesText);
        TextView goal = (TextView) findViewById(R.id.objectives);
        goal.setMovementMethod(new ScrollingMovementMethod());
        objective.setMovementMethod(new ScrollingMovementMethod());

    }

}
