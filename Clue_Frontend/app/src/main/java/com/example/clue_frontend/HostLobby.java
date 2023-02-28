package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class HostLobby extends AppCompatActivity{
    Integer numPlayers = 0;
    Button three;
    Button four;
    Button five;
    Button six;
    Button createLobby;

    EditText gameCode;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_hostlobby);

        three = (Button) findViewById(R.id.button3);
        four = (Button) findViewById(R.id.button4);
        five = (Button) findViewById(R.id.button5);
        six = (Button) findViewById(R.id.button6);
        createLobby = (Button) findViewById(R.id.lobbyCreator);

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 3;
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 4;
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 5;
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                v.setBackgroundColor(Color.GRAY);
                numPlayers = 6;
            }
        });

        createLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HostLobby.this, Lobby.class);
                gameCode = findViewById(R.id.gameCode);
                String gameCodeData = gameCode.getText().toString();
                if(gameCodeData.isEmpty()) {
                    gameCode.setError("Please Enter A Game Code!");
                } else if(numPlayers == 0) {
                    gameCode.setError("Please Select Maximum Players");
                } else {
//                TODO: VOLLEY ROUND TRIP HERE
                }
            }
        });
    }

    private void resetButtons() {
        three.setBackgroundColor(Color.parseColor("#08403E"));
        four.setBackgroundColor(Color.parseColor("#08403E"));
        five.setBackgroundColor(Color.parseColor("#08403E"));
        six.setBackgroundColor(Color.parseColor("#08403E"));

    }

}
