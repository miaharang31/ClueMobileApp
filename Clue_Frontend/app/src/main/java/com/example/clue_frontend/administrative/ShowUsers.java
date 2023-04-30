package com.example.clue_frontend.administrative;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.R;

import org.json.JSONArray;

public class ShowUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        String url = "http://coms-309-038.class.las.iastate.edu:8080/allUsers";

        RequestQueue queue = Volley.newRequestQueue(ShowUsers.this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            
                        }
                    }
                },
                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ShowUsers.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);

    }
}