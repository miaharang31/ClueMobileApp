package com.example.clue_frontend;

import static com.example.clue_frontend.GameView.turn;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Game extends AppCompatActivity {
    RelativeLayout relativeLayout;
    SwipeListener swipeListener;
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
        setContentView(R.layout.board);

        //Sets up the swipe lister
        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);
    }

    private class SwipeListener implements View.OnTouchListener {
        //Tutorial crap
        GestureDetector gestureDetector;

        SwipeListener(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                //IDK
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                //When the user swipes:
                @Override
                public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e1.getY();

                    try {
                        //If the player still has moves
                        if(GameView.n > 0){
                            //If they swiped in the x-axis
                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                //If they swiped hard enough
                                if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                    if (xDiff > 0) {
                                        //Swiped right
                                        //**NOTE: I added a try catch while debugging the out of bounds error, idk if we need it
                                        try {
                                            //If you are not starting on the edge and if there's no edge when you turn right:
                                            if ((GameView.arrBoard.get(turn.getPlacement() + 1).getBm() != GameView.edge) && (turn.getPlacement() % 23 != 22)) {
                                                //Turn right
                                                GameView.TurnRight();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        //Swiped left
                                        try {
                                            //If you are not starting on the edge and if there's no edge when you turn left:
                                            if ((GameView.arrBoard.get(turn.getPlacement() - 1).getBm() != GameView.edge) && (turn.getPlacement() % 23 != 0)) {
                                                //Turn left
                                                GameView.TurnLeft();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }
                            }
                            //If they swiped in the y-axis
                            else {
                                //If they swiped hard enough
                                if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold) {
                                    if (yDiff > 0) {
                                        //Swiped down
                                        try {
                                            //If you are not starting on the edge and if there's no edge if you move down:
                                            if(GameView.arrBoard.get(GameView.turn.getPlacement() + 22).getBm() != GameView.edge && turn.getPlacement() < 462){
                                                //Move down
                                                GameView.MoveDown();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }else {
                                        //Swiped up
                                        try {
                                            //If you are not starting on the edge and if there's no edge if you move up:
                                            if(GameView.arrBoard.get(GameView.turn.getPlacement() - 22).getBm() != GameView.edge && turn.getPlacement() > 22){
                                                //Move up
                                                GameView.MoveUp();
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }
                            }
                        }
                        //If they run out of moves:
                        else {
                            //Sets the turn to the next player based on how many players there are
                            //Turn order goes for 3 players: player1, 2, 3, 1 ...
                            //                    4 players: player1, 2, 3, 4, 1 ...
                            //                    ect...
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

                            //**NOTE: Plug in dice number roll for n
                            GameView.n = GameView.rand.nextInt(11) + 1;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return false;
                }
            };

            //TUTORIAL.
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }


        //Yep, more tutorial stuff
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }
}


