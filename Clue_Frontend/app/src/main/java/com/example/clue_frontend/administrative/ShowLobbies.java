package com.example.clue_frontend.administrative;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.R;

import org.json.JSONArray;

public class ShowLobbies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lobbies);

        String url = "http://coms-309-038.class.las.iastate.edu:8080/lobby";

        RequestQueue queue = Volley.newRequestQueue(ShowLobbies.this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // TODO: display all lobbies
                    }
                },
                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        //TODO: toast
                    }
                });
        queue.add(request);
    }
}