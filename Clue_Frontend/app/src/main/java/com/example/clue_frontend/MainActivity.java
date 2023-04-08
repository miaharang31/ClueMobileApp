package com.example.clue_frontend;

import static com.example.clue_frontend.GamePlay.GameView.turn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.GamePlay.Constraints;
import com.example.clue_frontend.GamePlay.Game;
import com.example.clue_frontend.GamePlay.GameView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    SwipeListener swipeListener;

/*for scroll outside override*/
//    TextView textView;
//    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_main); //activity_main


        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextView newPlayerLink = findViewById(R.id.NewPlayerLink);

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
                                MyApplication app = (MyApplication) getApplication();
                                try {
                                    app.setUserid((Integer) response.get("id"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
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

    private class SwipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListener(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e1.getY();

                    try {
                        if(GameView.n > 0){
                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                    if (xDiff > 0) {
                                        //Swiped right
                                        try {
                                            if ((GameView.arrBoard.get(turn.getPlacement() + 1).getBm() != GameView.edge) && (turn.getPlacement() % 23 != 22)) {
                                                GameView.TurnRight();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        //Swiped left
                                        try {
                                            if ((GameView.arrBoard.get(turn.getPlacement() - 1).getBm() != GameView.edge) && (turn.getPlacement() % 23 != 0)) {
                                                GameView.TurnLeft();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }
                            } else {
                                if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold) {

                                    if (yDiff > 0) {
                                        //Swiped down
                                        try {
                                            if(GameView.arrBoard.get(GameView.turn.getPlacement() + 22).getBm() != GameView.edge && turn.getPlacement() < 462){
                                                GameView.MoveDown();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }else {
                                        //Swiped up
                                        try {
                                            if(GameView.arrBoard.get(GameView.turn.getPlacement() - 22).getBm() != GameView.edge && turn.getPlacement() > 22){
                                                GameView.MoveUp();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }
                            }
                        }else {
                            switch (GameView.number_of_players){
                                case 4:
                                    if(turn == GameView.player1){
                                        turn = GameView.player2;
                                        System.out.println("******************** player2's turn(white)");
                                    }else if(turn == GameView.player2){
                                        turn = GameView.player3;
                                        System.out.println("******************** player3's turn(plum)");
                                    }else if(turn == GameView.player3){
                                        turn = GameView.player4;
                                        System.out.println("******************** player4's turn(mustard)");
                                    }else{
                                        turn = GameView.player1;
                                        System.out.println("******************** player1's turn(scarlet)");
                                    }
                                    break;
                                case 5:
                                    if(turn == GameView.player1){
                                        turn = GameView.player2;
                                        System.out.println("******************** player2's turn(white)");
                                    }else if(turn == GameView.player2){
                                        turn = GameView.player3;
                                        System.out.println("******************** player3's turn(plum)");
                                    }else if(turn == GameView.player3){
                                        turn = GameView.player4;
                                        System.out.println("******************** player4's turn(mustard)");
                                    }else if(turn == GameView.player4){
                                        turn = GameView.player5;
                                        System.out.println("******************** player5's turn(green)");
                                    }else{
                                        turn = GameView.player1;
                                        System.out.println("******************** player1's turn(scarlet)");
                                    }
                                    break;
                                case 6:
                                    if(turn == GameView.player1){
                                        turn = GameView.player2;
                                        System.out.println("******************** player2's turn(white)");
                                    }else if(turn == GameView.player2){
                                        turn = GameView.player3;
                                        System.out.println("******************** player3's turn(plum)");
                                    }else if(turn == GameView.player3){
                                        turn = GameView.player4;
                                        System.out.println("******************** player4's turn(mustard)");
                                    }else if(turn == GameView.player4){
                                        turn = GameView.player5;
                                        System.out.println("******************** player5's turn(green)");
                                    }else if(turn == GameView.player5){
                                        turn = GameView.player6;
                                        System.out.println("******************** player6's turn(peacock)");
                                    }else {
                                        turn = GameView.player1;
                                        System.out.println("******************** player1's turn(scarlet)");
                                    }
                                    break;
                                default:
                                    if(turn == GameView.player1){
                                        turn = GameView.player2;
                                        System.out.println("******************** player2's turn(white)");
                                    }else if(turn == GameView.player2){
                                        turn = GameView.player3;
                                        System.out.println("******************** player3's turn(plum)");
                                    }else{
                                        turn = GameView.player1;
                                        System.out.println("******************** player1's turn(scarlet)");
                                    }
                            }

                            GameView.n = GameView.rand.nextInt(11) + 1;;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return false;
                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }
}