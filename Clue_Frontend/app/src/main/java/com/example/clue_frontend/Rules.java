package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clue_frontend.Lobbies.HostLobby;

public class Rules extends AppCompatActivity {

    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_page);
        TextView objective = (TextView) findViewById(R.id.rulesText);
        TextView goal = (TextView) findViewById(R.id.objectives);
        home = (Button) findViewById(R.id.account_button);
        goal.setMovementMethod(new ScrollingMovementMethod());
        objective.setMovementMethod(new ScrollingMovementMethod());
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Rules.this, Home.class);
                startActivity(intent);
            }
        });
    }

}
