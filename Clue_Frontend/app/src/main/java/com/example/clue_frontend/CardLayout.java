package com.example.clue_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clue_frontend.GamePlay.Game;
import com.example.clue_frontend.databinding.CardLayoutBinding;

public class CardLayout extends AppCompatActivity {


    private CardLayoutBinding binding;

    public void exitName(View v) {
        startActivity(new Intent(CardLayout.this, Game.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CardLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        btn = (Button) findViewById(R.id.exitbtn);
        int[] images = {R.drawable.green, R.drawable.mustard_card, R.drawable.peacock, R.drawable.plum, R.drawable.scarlet_card, R.drawable.white, R.drawable.candlestick_card,R.drawable.knife_card,R.drawable.lead_pipe_card,R.drawable.revolver_card,R.drawable.rope_card,R.drawable.wrench_card,R.drawable.ballroom_card,R.drawable.billiard_card,R.drawable.conservatory_card,R.drawable.dining_card,R.drawable.hall_card,R.drawable.kitchen_card,R.drawable.library_card,R.drawable.lounge_card,R.drawable.study_card};
        String[] names = {"Mr. Green", "Colonel Mustard", "Mrs. Peacock", "Professor Plum", "Miss Scarlet", "Mrs. White", "the candlestick", "the knife", "the lead pipe", "the revolver", "the rope", "the wrench", "the ballroom", "the billiard room", "the conservatory", "the dining room", "the hall", "the kithcen", "the library", "the lounge", "the study"};
        GridAdapter gridAdapter = new GridAdapter(CardLayout.this, images);
        binding.gridViewCards.setAdapter(gridAdapter);

        binding.gridViewCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CardLayout.this,"Do you want to show " + names[position] + " to the person who's turn it is?", Toast.LENGTH_SHORT).show();
            }
        });




//        MyApplication app = (MyApplication) getApplication();

//        Random rand = new Random();
//        if (app.getUsersplaying() == 3) {
//            int arr = new int[6];
//        }
//        else if (app.getUsersplaying() == 4) {
//            int arr = new int[6]; //make the extra cards in everyones hands
//        }
//        else if (app.getUsersplaying() == 5) {
//            int arr = new int[7];
//        }
//        else if (app.getUsersplaying() == 6) {
//            int arr = new int[3];
//        }


//        int[] arr = new int[10];
//        int[] draws = new int[10];
//        String[] sarr = new String[10];
//
//        for (int i = 0; i < arr.length;i++) {
//            arr[i] = rand.nextInt(22);
//        }
//
//        String url = "http://coms-309-038.class.las.iastate.edu:8080/getCardById";
//        for (int j = 0; j < sarr.length; j++) {
//            RequestQueue queue = Volley.newRequestQueue(CardLayout.this);
//            JSONObject json = null;
//            try {
//                json = new JSONObject();
//                json.put("cardID", arr[j]);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            int finalJ = j;
//            JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.POST, url, json,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            MyApplication app = (MyApplication) getApplication();
//                            try {
//                                String drawableName = (String) response.get("cardID");
//                                sarr[finalJ] = drawableName;
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(CardLayout.this, "Error: " + error, Toast.LENGTH_SHORT).show();
//                            Log.d("ResponseError", error.toString());
//                        }
//                    });
//
//            queue.add(jreq);
//        }
//        for (int k = 0; k < arr.length; k++) {
//            draws[k] = getResources().getIdentifier(sarr[k],"drawable", getPackageName());
//        }
//
//        GridAdapter gridAdapter = new GridAdapter(CardLayout.this, draws);
//        binding.gridViewCards.setAdapter(gridAdapter);




    }
}
