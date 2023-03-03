package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Lobby extends AppCompatActivity {

    HostLobby newLobby;
    String hostName;
    EditText host;
    String[] playerNames = new String[newLobby.numPlayers];
    EditText[] names = new EditText[newLobby.numPlayers];
    Button startGame;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        startGame = (Button)findViewById(R.id.startGame);
        host = findViewById(R.id.host);
        String currentName;
        int topPadding = 150;

        // insert host name
        host.setText(hostName);

        // create text boxes with each player's name
        for(int i = 0; i <= playerNames.length; i++){

            currentName = playerNames[i];
            names[i].setText(currentName);
            names[i].setPadding(45, topPadding, 45, 100);
            //names[i].setBackgroundColor(#);
            topPadding+= 50;
            count++;
        }

        // start game
        startGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Lobby.this, StartGame.class);
                if(count == newLobby.numPlayers){
                    startActivity(intent);
                }
            }
        });
    }


}
