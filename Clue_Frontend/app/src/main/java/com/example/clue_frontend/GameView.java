package com.example.clue_frontend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;


public class GameView extends View {
    static Random rand = new Random();

    //put dice number here:
    public static int n = rand.nextInt(11) + 1;

    static int number_of_players = 6;
    static Bitmap edge;
    private static Bitmap tile1;
    private static Bitmap tile2;
    private static Bitmap study;
    private static Bitmap library;
    private static Bitmap billiard;
    private static Bitmap conservatory;
    private static Bitmap hall;
    private static Bitmap clue;
    private static Bitmap ball;
    private static Bitmap lounge;
    private static Bitmap dinning;
    private static Bitmap kitchen;
    private static Bitmap scarlet_start;
    private static Bitmap white_start;
    private static Bitmap plum_start;
    private static Bitmap mustard_start;
    private static Bitmap green_start;
    private static Bitmap peacock_start;
    private static Bitmap scarlet;
    private static Bitmap white;
    private static Bitmap plum;
    private static Bitmap mustard;
    private static Bitmap green;
    private static Bitmap peacock;

    public static int sizeOfMap = 35 * Constraints.SCREEN_WIDTH / 1000;
    private int h = 22, w = 22;
    public static ArrayList<Tile> arrBoard = new ArrayList<>();
    public static Player player1, player2, player3, player4, player5, player6;

    public static Player turn = player1;
    Handler handler;
    Runnable r;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("************* n:" + n + "\n");

        //creates tiles for the boards
        edge = BitmapFactory.decodeResource(this.getResources(), R.drawable.empty);
        edge = Bitmap.createScaledBitmap(edge, sizeOfMap, sizeOfMap, true);
        tile1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.dark);
        tile1 = Bitmap.createScaledBitmap(tile1, sizeOfMap, sizeOfMap, true);
        tile2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.light);
        tile2 = Bitmap.createScaledBitmap(tile2, sizeOfMap, sizeOfMap, true);

        //create starting places
        scarlet_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet_start);
        scarlet_start = Bitmap.createScaledBitmap(scarlet_start, 56, 43, true);
        white_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.white_start);
        white_start = Bitmap.createScaledBitmap(white_start, 49, 46, true);
        plum_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum_start);
        plum_start = Bitmap.createScaledBitmap(plum_start, 44, 40, true);
        mustard_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard_start);
        mustard_start = Bitmap.createScaledBitmap(mustard_start, 44, 50, true);
        green_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.green_start);
        green_start = Bitmap.createScaledBitmap(green_start, 47, 43, true);
        peacock_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.peacock_start);
        peacock_start = Bitmap.createScaledBitmap(peacock_start, 46, 42, true);

        //create rooms
        study = BitmapFactory.decodeResource(this.getResources(), R.drawable.study);
        study = Bitmap.createScaledBitmap(study, 242, 205, true);
        library = BitmapFactory.decodeResource(this.getResources(), R.drawable.library);
        library = Bitmap.createScaledBitmap(library, 266, 230, true);
        billiard = BitmapFactory.decodeResource(this.getResources(), R.drawable.billiard_room);
        billiard = Bitmap.createScaledBitmap(billiard, 282, 174, true);
        conservatory = BitmapFactory.decodeResource(this.getResources(), R.drawable.conservatory);
        conservatory = Bitmap.createScaledBitmap(conservatory, 165, 205, true);
        hall = BitmapFactory.decodeResource(this.getResources(), R.drawable.hall);
        hall = Bitmap.createScaledBitmap(hall, 355, 215, true);
        clue = BitmapFactory.decodeResource(this.getResources(), R.drawable.clue);
        clue = Bitmap.createScaledBitmap(clue, 157, 240, true);
        ball = BitmapFactory.decodeResource(this.getResources(), R.drawable.ball_room);
        ball = Bitmap.createScaledBitmap(ball, 419, 306, true);
        lounge = BitmapFactory.decodeResource(this.getResources(), R.drawable.lounge);
        lounge = Bitmap.createScaledBitmap(lounge, 242, 207, true);
        dinning = BitmapFactory.decodeResource(this.getResources(), R.drawable.dinning_room);
        dinning = Bitmap.createScaledBitmap(dinning, 301, 260, true);
        kitchen = BitmapFactory.decodeResource(this.getResources(), R.drawable.kitchen);
        kitchen = Bitmap.createScaledBitmap(kitchen, 206, 208, true);

        //creates board
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (j == 0 | i == 0 | i == (h - 1) | j == (w - 1)) {
                    arrBoard.add(new Tile(edge, j * sizeOfMap + Constraints.SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                } else if ((i + j) % 2 == 0) {
                    arrBoard.add(new Tile(tile1, j * sizeOfMap + Constraints.SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                } else {
                    arrBoard.add(new Tile(tile2, j * sizeOfMap + Constraints.SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                }
            }
        }

        //set up players and player pieces based on how many players
        switch (number_of_players) {
            case 4:
                scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet);
                scarlet = Bitmap.createScaledBitmap(scarlet, 33, 30, true);
                white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white);
                white = Bitmap.createScaledBitmap(white, 30, 30, true);
                plum = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum);
                plum = Bitmap.createScaledBitmap(plum, 30, 30, true);
                mustard = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard);
                mustard = Bitmap.createScaledBitmap(mustard, 34, 30, true);

                player1 = new Player(scarlet, 468, 0, 0);
                player1.setX(arrBoard.get(player1.getPlacement()).getTileX() + 3);
                player1.setY(arrBoard.get(player1.getPlacement()).getTileY() + 3);

                turn = player1;

                player2 = new Player(white, 476, 0, 0);
                player2.setX(arrBoard.get(player2.getPlacement()).getTileX() + 5);
                player2.setY(arrBoard.get(player2.getPlacement()).getTileY() + 3);

                player3 = new Player(plum, 330, 0, 0);
                player3.setX(arrBoard.get(player3.getPlacement()).getTileX() + 4);
                player3.setY(arrBoard.get(player3.getPlacement()).getTileY() + 6);

                player4 = new Player(mustard, 351, 0, 0);
                player4.setX(arrBoard.get(player4.getPlacement()).getTileX() + 1);
                player4.setY(arrBoard.get(player4.getPlacement()).getTileY() + 3);
                break;
            case 5:
                scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet);
                scarlet = Bitmap.createScaledBitmap(scarlet, 30, 30, true);
                white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white);
                white = Bitmap.createScaledBitmap(white, 30, 30, true);
                plum = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum);
                plum = Bitmap.createScaledBitmap(plum, 30, 30, true);
                mustard = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard);
                mustard = Bitmap.createScaledBitmap(mustard, 34, 30, true);
                green = BitmapFactory.decodeResource(this.getResources(), R.drawable.green);
                green = Bitmap.createScaledBitmap(green, 30, 30, true);

                player1 = new Player(scarlet, 468, 0, 0);
                player1.setX(arrBoard.get(player1.getPlacement()).getTileX() + 3);
                player1.setY(arrBoard.get(player1.getPlacement()).getTileY() + 3);

                turn = player1;

                player2 = new Player(white, 476, 0, 0);
                player2.setX(arrBoard.get(player2.getPlacement()).getTileX() + 5);
                player2.setY(arrBoard.get(player2.getPlacement()).getTileY() + 3);

                player3 = new Player(plum, 330, 0, 0);
                player3.setX(arrBoard.get(player3.getPlacement()).getTileX() + 4);
                player3.setY(arrBoard.get(player3.getPlacement()).getTileY() + 6);

                player4 = new Player(mustard, 351, 0, 0);
                player4.setX(arrBoard.get(player4.getPlacement()).getTileX() + 1);
                player4.setY(arrBoard.get(player4.getPlacement()).getTileY() + 3);

                player5 = new Player(green, 14, 0, 0);
                player5.setX(arrBoard.get(player5.getPlacement()).getTileX() + 3);
                player5.setY(arrBoard.get(player5.getPlacement()).getTileY() + 3);

                break;
            case 6:
                scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet);
                scarlet = Bitmap.createScaledBitmap(scarlet, 31, 30, true);
                white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white);
                white = Bitmap.createScaledBitmap(white, 30, 30, true);
                plum = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum);
                plum = Bitmap.createScaledBitmap(plum, 30, 30, true);
                mustard = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard);
                mustard = Bitmap.createScaledBitmap(mustard, 34, 30, true);
                green = BitmapFactory.decodeResource(this.getResources(), R.drawable.green);
                green = Bitmap.createScaledBitmap(green, 30, 30, true);
                peacock = BitmapFactory.decodeResource(this.getResources(), R.drawable.peacock);
                peacock = Bitmap.createScaledBitmap(peacock, 30, 30, true);

                player1 = new Player(scarlet, 468, 0, 0);
                player1.setX(arrBoard.get(player1.getPlacement()).getTileX() + 3);
                player1.setY(arrBoard.get(player1.getPlacement()).getTileY() + 3);

                turn = player1;

                player2 = new Player(white, 476, 0, 0);
                player2.setX(arrBoard.get(player2.getPlacement()).getTileX() + 5);
                player2.setY(arrBoard.get(player2.getPlacement()).getTileY() + 3);

                player3 = new Player(plum, 330, 0, 0);
                player3.setX(arrBoard.get(player3.getPlacement()).getTileX() + 4);
                player3.setY(arrBoard.get(player3.getPlacement()).getTileY() + 6);

                player4 = new Player(mustard, 351, 0, 0);
                player4.setX(arrBoard.get(player4.getPlacement()).getTileX() + 1);
                player4.setY(arrBoard.get(player4.getPlacement()).getTileY() + 3);

                player5 = new Player(green, 14, 0, 0);
                player5.setX(arrBoard.get(player5.getPlacement()).getTileX() + 3);
                player5.setY(arrBoard.get(player5.getPlacement()).getTileY() + 3);

                player6 = new Player(peacock, 7, 0, 0);
                player6.setX(arrBoard.get(player6.getPlacement()).getTileX() + 3);
                player6.setY(arrBoard.get(player6.getPlacement()).getTileY() + 5);
                break;
            default:
                scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet);
                scarlet = Bitmap.createScaledBitmap(scarlet, 30, 30, true);
                white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white);
                white = Bitmap.createScaledBitmap(white, 30, 30, true);
                plum = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum);
                plum = Bitmap.createScaledBitmap(plum, 30, 30, true);

                player1 = new Player(scarlet, 468, 0, 0);
                player1.setX(arrBoard.get(player1.getPlacement()).getTileX() + 3);
                player1.setY(arrBoard.get(player1.getPlacement()).getTileY() + 3);

                turn = player1;

                player2 = new Player(white, 476, 0, 0);
                player2.setX(arrBoard.get(player2.getPlacement()).getTileX() + 3);
                player2.setY(arrBoard.get(player2.getPlacement()).getTileY() + 3);

                player3 = new Player(plum, 330, 0, 0);
                player3.setX(arrBoard.get(player3.getPlacement()).getTileX() + 4);
                player3.setY(arrBoard.get(player3.getPlacement()).getTileY() + 6);

        }


        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draws board
        for (int i = 0; i < arrBoard.size(); i++) {
            canvas.drawBitmap(arrBoard.get(i).getBm(), arrBoard.get(i).getTileX(),
                    arrBoard.get(i).getTileY(), null);
        }

        //draws starting places
        canvas.drawBitmap(scarlet_start, 345, 1330, null);
        canvas.drawBitmap(white_start, 646, 1328, null);
        canvas.drawBitmap(plum_start, 130, 1112, null);
        canvas.drawBitmap(mustard_start, 907, 1107, null);
        canvas.drawBitmap(green_start, 646, 552, null);
        canvas.drawBitmap(peacock_start, 387, 554, null);

        //draws rooms
        canvas.drawBitmap(study, 156, 583, null);
        canvas.drawBitmap(library, 149, 715, null);
        canvas.drawBitmap(billiard, 158, 950, null);
        canvas.drawBitmap(conservatory, 160, 1138, null);
        canvas.drawBitmap(hall, 411, 580, null);
        canvas.drawBitmap(clue, 461, 807, null);
        canvas.drawBitmap(ball, 297, 1055, null);
        canvas.drawBitmap(lounge, 676, 582, null);
        canvas.drawBitmap(dinning, 627, 829, null);
        canvas.drawBitmap(kitchen, 715, 1135, null);


        //draws player pieces based on how many players
        switch (number_of_players) {
            case 4:
                canvas.drawBitmap(player1.getBm(), player1.getX(), player1.getY(), null);
                canvas.drawBitmap(player2.getBm(), player2.getX(), player2.getY(), null);
                canvas.drawBitmap(player3.getBm(), player3.getX(), player3.getY(), null);
                canvas.drawBitmap(player4.getBm(), player4.getX(), player4.getY(), null);
                break;
            case 5:
                canvas.drawBitmap(player1.getBm(), player1.getX(), player1.getY(), null);
                canvas.drawBitmap(player2.getBm(), player2.getX(), player2.getY(), null);
                canvas.drawBitmap(player3.getBm(), player3.getX(), player3.getY(), null);
                canvas.drawBitmap(player4.getBm(), player4.getX(), player4.getY(), null);
                canvas.drawBitmap(player5.getBm(), player5.getX(), player5.getY(), null);
                break;
            case 6:
                canvas.drawBitmap(player1.getBm(), player1.getX(), player1.getY(), null);
                canvas.drawBitmap(player2.getBm(), player2.getX(), player2.getY(), null);
                canvas.drawBitmap(player3.getBm(), player3.getX(), player3.getY(), null);
                canvas.drawBitmap(player4.getBm(), player4.getX(), player4.getY(), null);
                canvas.drawBitmap(player5.getBm(), player5.getX(), player5.getY(), null);
                canvas.drawBitmap(player6.getBm(), player6.getX(), player6.getY(), null);
                break;
            default:
                canvas.drawBitmap(player1.getBm(), player1.getX(), player1.getY(), null);
                canvas.drawBitmap(player2.getBm(), player2.getX(), player2.getY(), null);
                canvas.drawBitmap(player3.getBm(), player3.getX(), player3.getY(), null);
        }
        handler.postDelayed(r, 100);

    }

    public static void TurnLeft() {
        n--;
        System.out.println("********************** TurnLeft updated n: " + GameView.n + "\n");
        turn.setPlacement(turn.getPlacement() - 1);
        turn.setX(GameView.arrBoard.get(turn.getPlacement()).getTileX() + 3);
        turn.setY(GameView.arrBoard.get(turn.getPlacement()).getTileY() + 3);

    }

    public static void TurnRight() {
        n--;
        System.out.println("********************** TurnRight updated n: " + GameView.n + "\n");
        turn.setPlacement(turn.getPlacement() + 1);
        turn.setX(GameView.arrBoard.get(turn.getPlacement()).getTileX() + 3);
        turn.setY(GameView.arrBoard.get(turn.getPlacement()).getTileY() + 3);

    }

    public static void MoveUp() {

        n--;
        System.out.println("********************** MoveUp updated n: " + GameView.n + "\n");
        turn.setPlacement(turn.getPlacement() - 22);
        turn.setX(GameView.arrBoard.get(turn.getPlacement()).getTileX() + 3);
        turn.setY(GameView.arrBoard.get(turn.getPlacement()).getTileY() + 3);


    }

    public static void MoveDown() {

        //(arrBoard.get(turn.getPlacement() + 22).getBm() != edge) && (turn.getPlacement() < 462)
        n--;
        System.out.println("********************** MoveDown updated n: " + GameView.n + "\n");
        turn.setPlacement(turn.getPlacement() + 22);
        turn.setX(GameView.arrBoard.get(turn.getPlacement()).getTileX() + 3);
        turn.setY(GameView.arrBoard.get(turn.getPlacement()).getTileY() + 3);

    }
}
