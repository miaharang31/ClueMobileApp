package com.example.clue_frontend.GamePlay.Sockets;


import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.clue_frontend.GamePlay.GameView;

public class SwipeListener implements View.OnTouchListener {
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
                    if(1 > 0){
                        if (Math.abs(xDiff) > Math.abs(yDiff)) {
                            if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                if (xDiff > 0) {
                                    //Swiped right
                                    try {
//                                        if ((com.example.clue_frontend.GamePlay.GameView.arrBoard.get(turn.getPlacement() + 1).getBm() != com.example.clue_frontend.GamePlay.GameView.edge) && (turn.getPlacement() % 23 != 22)) {
//                                            com.example.clue_frontend.GamePlay.GameView.TurnRight();
//                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                } else {
                                    //Swiped left
                                    try {
//                                        if ((com.example.clue_frontend.GamePlay.GameView.arrBoard.get(turn.getPlacement() - 1).getBm() != com.example.clue_frontend.GamePlay.GameView.edge) && (turn.getPlacement() % 23 != 0)) {
//                                            com.example.clue_frontend.GamePlay.GameView.TurnLeft();
//                                        }
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
//                                        if(com.example.clue_frontend.GamePlay.GameView.arrBoard.get(com.example.clue_frontend.GamePlay.GameView.turn.getPlacement() + 22).getBm() != com.example.clue_frontend.GamePlay.GameView.edge && turn.getPlacement() < 462){
//                                            com.example.clue_frontend.GamePlay.GameView.MoveDown();
//                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }else {
                                    //Swiped up
                                    try {
//                                        if(com.example.clue_frontend.GamePlay.GameView.arrBoard.get(com.example.clue_frontend.GamePlay.GameView.turn.getPlacement() - 22).getBm() != com.example.clue_frontend.GamePlay.GameView.edge && turn.getPlacement() > 22){
//                                            com.example.clue_frontend.GamePlay.GameView.MoveUp();
//                                        }
                                    }catch (Exception e){
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
