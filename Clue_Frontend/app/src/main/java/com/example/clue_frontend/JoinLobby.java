package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JoinLobby extends AppCompatActivity {
    Button joinLobby;
    EditText gameCode;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_joinlobby);

        joinLobby = (Button) findViewById(R.id.joinbtn);
        joinLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinLobby.this, Lobby.class);
                gameCode = findViewById(R.id.gameCode);
                String gameCodeData = gameCode.getText().toString();
                if(gameCodeData.isEmpty()) {
                    gameCode.setError("Game Code cannot be empty!");
                } else {
//                  TODO: VOLLEY ROUND TRIP HERE
                }

            }
        });
    }
}
