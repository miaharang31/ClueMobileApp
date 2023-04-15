package com.example.clue_frontend.GamePlay;

import static com.example.clue_frontend.GamePlay.GameView.turn;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clue_frontend.Lobbies.Lobby;
import com.example.clue_frontend.MyApplication;
import com.example.clue_frontend.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Game extends AppCompatActivity {
    View relativeLayout = findViewById(R.id.relative_layout);
    SwipeListener swipeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.board);

        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);

    }

    public String getCharacter() {
        final String[] name = new String[1];
        RequestQueue queue = Volley.newRequestQueue(Game.this);
        MyApplication app = (MyApplication) getApplication();
        String url = "http://coms-309-038.class.las.iastate.edu:8080/info/player/role" + app.getUserid();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            name[0] = response.get("name").toString();
                            System.out.println("name[0]: " + name[0]);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        //return null;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        return name[0];
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


