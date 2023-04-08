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

import com.example.clue_frontend.MainActivity;
import com.example.clue_frontend.R;


public class Game extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //No idea what this stuff does. Blame the tutorial
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Sets the board up based on device's screen
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;
        View relativeLayout = findViewById(R.id.relative_layout);
        MainActivity.SwipeListener swipeListener = new MainActivity.SwipeListener(relativeLayout);
        setContentView(R.layout.board);

        //Sets up the swipe lister

        class SwipeListener implements View.OnTouchListener {


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
                                            if ((GameView.arrBoard.get(turn.getPlacement() + 1).getBm() != GameView.edge) && (turn.getPlacement() % 23 != 22)) {
                                                GameView.TurnRight();
                                            }

                                        } else {
                                            //Swiped left
                                            try {
                                                if ((GameView.arrBoard.get(turn.getPlacement() - 1).getBm() != GameView.edge) && (turn.getPlacement() % 23 != 0)) {
                                                    GameView.TurnLeft();
                                                }
                                            } catch (Exception e) {
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
                                                if (GameView.arrBoard.get(GameView.turn.getPlacement() + 22).getBm() != GameView.edge && turn.getPlacement() < 462) {
                                                    GameView.MoveDown();
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            //Swiped up
                                            try {
                                                if (GameView.arrBoard.get(GameView.turn.getPlacement() - 22).getBm() != GameView.edge && turn.getPlacement() > 22) {
                                                    GameView.MoveUp();
                                                }
                                            } catch (Exception e) {
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
}


