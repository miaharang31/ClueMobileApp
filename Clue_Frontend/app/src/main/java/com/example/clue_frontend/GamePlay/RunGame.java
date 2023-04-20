package com.example.clue_frontend.GamePlay;

import static com.example.clue_frontend.GamePlay.GameView.player;

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

//NOTE: Not connected from the lobby

public class RunGame extends AppCompatActivity {
    RelativeLayout relativeLayout;
    SwipeListener swipeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;

        System.out.println("In RunGame");

        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);
        setContentView(R.layout.activity_main);
    }

    static class SwipeListener implements View.OnTouchListener {
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
                        if(GameView.moves > 0){
                            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                                if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                    if (xDiff > 0) {
                                        //Swiped right
                                        if ((GameView.arrBoard.get(player.getPlacement() + 1).getBm() != GameView.edge) && (player.getPlacement() % 23 != 22)) {
                                            GameView.TurnRight();
                                        }

                                    } else {
                                        //Swiped left
                                        try {
                                            if ((GameView.arrBoard.get(player.getPlacement() - 1).getBm() != GameView.edge) && (player.getPlacement() % 23 != 0)) {
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
                                            if (GameView.arrBoard.get(GameView.player.getPlacement() + 22).getBm() != GameView.edge && player.getPlacement() < 462) {
                                                GameView.MoveDown();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        //Swiped up
                                        try {
                                            if (GameView.arrBoard.get(player.getPlacement() - 22).getBm() != GameView.edge && player.getPlacement() > 22) {
                                                GameView.MoveUp();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    return true;
                                }
                            }
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
