package com.example.boardtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import android.view.WindowManager;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.relative_layout);
        swipeListener = new SwipeListener(relativeLayout);

    }

    private class SwipeListener implements View.OnTouchListener{
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
                        if (Math.abs(xDiff) > Math.abs(yDiff)) {
                            if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                if (xDiff > 0){
                                   //Swiped right
                                    GameView.scarlet_placement = GameView.scarlet_placement + 1;
                                }else{
                                    //Swiped left
                                    GameView.scarlet_placement = GameView.scarlet_placement - 1;
                                }
                                return true;
                            }
                        }else{
                            if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold) {
                                if (yDiff > 0){
                                    //Swiped down
                                    GameView.scarlet_placement = GameView.scarlet_placement + 22;
                                }else{
                                    //Swiped up
                                    GameView.scarlet_placement = GameView.scarlet_placement - 22;
                                }
                                return true;
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