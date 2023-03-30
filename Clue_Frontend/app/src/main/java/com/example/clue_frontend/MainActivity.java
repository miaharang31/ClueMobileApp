package com.example.clue_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

/*for scroll outside override*/
//    TextView textView;
//    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main); //activity_main
//GRACE USES FOR RULES PAGE
//        setContentView(R.layout.rules_page); //activity_main

//        textView = (TextView) findViewById(R.id.rulesText);
//        textView.setMovementMethod(new ScrollingMovementMethod());
//
//        tv = (TextView) findViewById(R.id.objectives);
//        tv.setMovementMethod(new ScrollingMovementMethod());
//
        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextView newPlayerLink = findViewById(R.id.NewPlayerLink);

//      USED FOR TESTING
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoinLobby.class);
                startActivity(intent);
            }
        });

        //When the sign in button is clicked and data is entered for usename and password
        submitButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            EditText username = findViewById(R.id.loginUsername);
            EditText password = findViewById(R.id.loginPassword);
            String usernameData = username.getText().toString();
            String passwordData = password.getText().toString();
            String firstNameData = " ";
            String lastNameData = " ";
            String emailData = " ";
            boolean checkUsername, checkPassword;

            if(usernameData.isEmpty()){
                username.setError("Username cannot be empty");
                checkUsername = false;
            }else{
                username.setError(null);
                checkUsername = true;
            }

            if(passwordData.isEmpty()){
                password.setError("Password cannot be empty");
                checkPassword = false;
            }else{
                password.setError(null);
                checkPassword = true;
            }

            // if all textboxes are correct, all data will be added to the SecondActivity (main page for either regular/premium users) and will start
            if (checkUsername == true && checkPassword == true){
//                intent.putExtra("firstNameData",firstNameData);
//                intent.putExtra("lastNameData",lastNameData);
//                intent.putExtra("emailData",emailData);
//                intent.putExtra("usernameData",usernameData);
//                intent.putExtra("passwordData",passwordData);

//                startActivity(intent);

                String url = "http://coms-309-038.class.las.iastate.edu:8080/login";
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                JSONObject json = null;
                try {
                    json = new JSONObject();
                    json.put("username", usernameData);
                    json.put("password", passwordData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.POST, url, json,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
//                                String url2 = "http://coms-309-038.class.las.iastate.edu:8080/getUserId/";
//                                url2.concat(usernameData);
//                                RequestQueue request1 = Volley.newRequestQueue(MainActivity.this);
////
//                                JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET, url, null,
//                                        new Response.Listener<JSONObject>() {
//                                            @Override
//                                            public void onResponse(JSONObject response) {
//                                                int userID = (int) response;//THIS IS THE FAIL (ASK TA)
//                                                MyApplication app = (MyApplication) getApplication();
//                                                app.setUserid(userID);
//                                            }
//                                        },
//                                        new Response.ErrorListener() {
//                                            @Override
//                                            public void onErrorResponse(VolleyError error) {
//                                                Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
//                                                Log.d("ResponseError", error.toString());
//                                            }
//                                        });
//
//                                queue.add(jreq);
//                                MyApplication app = (MyApplication) getApplication();
//                                app.setUserid(response.);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                                Log.d("ResponseError", error.toString());
                            }
                        });

                queue.add(jreq);
            }

        }
    });

        //when the new player link is clicked, go to th SignUpActivity
        newPlayerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserSignUp.class);

                startActivity(intent);

            }
        });
    }
}