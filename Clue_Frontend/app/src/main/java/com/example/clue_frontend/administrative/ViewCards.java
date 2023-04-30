package com.example.clue_frontend.administrative;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.Lobbies.Lobby;
import com.example.clue_frontend.R;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewCards extends AppCompatActivity {

    Button exit;
    ImageView[] suspects;
    ImageView[] weapons;
    ImageView[] rooms;


    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        exit = findViewById(R.id.exitCards);
        // filling suspect array
        suspects = new ImageView[]{findViewById(R.id.mustardCard), findViewById(R.id.plumCard),
                                   findViewById(R.id.greenCard), findViewById(R.id.peacockCard),
                                   findViewById(R.id.scarletCard), findViewById(R.id.whiteCard)};
        // filling weapons array
        weapons = new ImageView[]{findViewById(R.id.knifeCard), findViewById(R.id.candlestickCard),
                                  findViewById(R.id.revolverCard), findViewById(R.id.ropeCard),
                                  findViewById(R.id.pipeCard), findViewById(R.id.wrenchCard)};

        rooms = new ImageView[]{findViewById(R.id.hallCard), findViewById(R.id.loungeCard),
                findViewById(R.id.diningRoomCard), findViewById(R.id.kitchenCard),
                findViewById(R.id.ballroomCard), findViewById(R.id.conservatoryCard),
                findViewById(R.id.billiardRoomCard), findViewById(R.id.libraryCard),
                findViewById(R.id.studyCard)};

        // setting suspect cards
        String url = "http://coms-309-038.class.las.iastate.edu:8080/card/suspect";
        RequestQueue queue = Volley.newRequestQueue(ViewCards.this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < suspects.length; i++){
                            try {
                                JSONObject card = response.getJSONObject(i);
                                Resources res = context.getResources();
                                Log.d("Suspect", card.getString("cardImage"));
                                int id = res.getIdentifier(card.getString("cardImage"), "drawable", getPackageName());
//                                Drawable d = ResourcesCompat.getDrawable(res, R.drawable.mustard, null);
                                suspects[i].setImageDrawable(res.getDrawable(id));
//                                        (response.getJSONObject(i).getInt("Mustard"));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewCards.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);

        // setting weapon cards
        url = "http://coms-309-038.class.las.iastate.edu:8080/card/weapon";
        queue = Volley.newRequestQueue(ViewCards.this);
        request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < weapons.length; i++){
//                            try {
//                               // weapons[i].setImageResource(response.getJSONObject(i).getInt("Mustard"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewCards.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);

        // setting weapon cards
        url = "http://coms-309-038.class.las.iastate.edu:8080/card/room";
        queue = Volley.newRequestQueue(ViewCards.this);
        request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < rooms.length; i++){
//                            try {
//                               // rooms[i].setImageResource(response.getJSONObject(i).getInt("Mustard"));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewCards.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewCards.this, AdminSettings.class);
                startActivity(intent);
            }
        });
    }
}