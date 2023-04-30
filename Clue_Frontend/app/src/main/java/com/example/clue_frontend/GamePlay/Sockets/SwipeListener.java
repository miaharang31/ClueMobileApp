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
                    System.out.println("blah");
                    if(1 > 0){
                    //if(com.example.clue_frontend.GamePlay.GameView.n > 0){
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
                    }else {
//                        switch (com.example.clue_frontend.GamePlay.GameView.number_of_players){
//                            case 4:
//                                if(turn == com.example.clue_frontend.GamePlay.GameView.player1){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player2;
//                                    System.out.println("******************** player2's turn(white)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player2){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player3;
//                                    System.out.println("******************** player3's turn(plum)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player3){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player4;
//                                    System.out.println("******************** player4's turn(mustard)");
//                                }else{
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player1;
//                                    System.out.println("******************** player1's turn(scarlet)");
//                                }
//                                break;
//                            case 5:
//                                if(turn == com.example.clue_frontend.GamePlay.GameView.player1){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player2;
//                                    System.out.println("******************** player2's turn(white)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player2){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player3;
//                                    System.out.println("******************** player3's turn(plum)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player3){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player4;
//                                    System.out.println("******************** player4's turn(mustard)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player4){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player5;
//                                    System.out.println("******************** player5's turn(green)");
//                                }else{
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player1;
//                                    System.out.println("******************** player1's turn(scarlet)");
//                                }
//                                break;
//                            case 6:
//                                if(turn == com.example.clue_frontend.GamePlay.GameView.player1){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player2;
//                                    System.out.println("******************** player2's turn(white)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player2){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player3;
//                                    System.out.println("******************** player3's turn(plum)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player3){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player4;
//                                    System.out.println("******************** player4's turn(mustard)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player4){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player5;
//                                    System.out.println("******************** player5's turn(green)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player5){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player6;
//                                    System.out.println("******************** player6's turn(peacock)");
//                                }else {
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player1;
//                                    System.out.println("******************** player1's turn(scarlet)");
//                                }
//                                break;
//                            default:
//                                if(turn == com.example.clue_frontend.GamePlay.GameView.player1){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player2;
//                                    System.out.println("******************** player2's turn(white)");
//                                }else if(turn == com.example.clue_frontend.GamePlay.GameView.player2){
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player3;
//                                    System.out.println("******************** player3's turn(plum)");
//                                }else{
//                                    turn = com.example.clue_frontend.GamePlay.GameView.player1;
//                                    System.out.println("******************** player1's turn(scarlet)");
//                                }
//                        }
//
//                        com.example.clue_frontend.GamePlay.GameView.n = GameView.rand.nextInt(11) + 1;;
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
