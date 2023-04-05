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

    //** NOTE: Put dice number roll for n:
    public static int n = rand.nextInt(11) + 1;

    //** NOTE: Put number of players for number_of_players
    static int number_of_players = 6;

    //Edge is the tan "border" sprites around the board.
    static Bitmap edge;
    //Tile1 and tile 2 are the dark brown and creme colored tile sprites
    //Tiles has five attribute things: Bitmap, where Bitmap acts as the tile's sprite,
    //                                 X, tiles x-axis on the screen,
    //                                 Y, tiles x-axis on the screen
    //                                 width: how wide the tiles are based on screen dimensions
    //                                 height: how tall the tiles are based on screen dimensions
    private static Bitmap tile1, tile2;
    // Room sprites
    // ** NOTE: To get boundaries for each room I will add a room class
    //         and put the boundaries for each room in there
    private static Bitmap study, library, billiard, conservatory,
            hall, clue, ball, lounge, dinning, kitchen;

    //Starting places for each character on the board
    private static Bitmap scarlet_start, white_start, plum_start,
            mustard_start, green_start, peacock_start;

    // Character sprites
    private static Bitmap scarlet, white, plum, mustard, green, peacock;

    //How big each tile is based on screen size
    public static int sizeOfTile = 35 * Constraints.SCREEN_WIDTH / 1000;

    //How many tiles for the width and height of the board
    private int h = 22, w = 22;

    //Holds all the tiles for the board
    public static ArrayList<Tile> arrBoard = new ArrayList<>();

    //All the possible players
    //Player has four attribute things: Bitmap, where Bitmap acts as the character's sprite,
    //                                  Placement: the specific tile where the player is on the board

    //**NOTE: I had to keep x and y values because when I only relied on placement the characters weren't centered
    //        on their respeced tile.
    //                                  X, players x-axis on the screen,
    //                                  Y, players x-axis on the screen
    public static Player player1, player2, player3, player4, player5, player6;

    //Player1 always starts their turn first
    public static Player turn = player1;

    //handler and r redraws the board for every movement, idk the tutorial told me to do it
    Handler handler;
    Runnable r;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("************* n:" + n + "\n");

        //creates tiles for the board sprites
        edge = BitmapFactory.decodeResource(this.getResources(), R.drawable.empty);
        edge = Bitmap.createScaledBitmap(edge, sizeOfTile, sizeOfTile, true);
        tile1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.dark);
        tile1 = Bitmap.createScaledBitmap(tile1, sizeOfTile, sizeOfTile, true);
        tile2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.light);
        tile2 = Bitmap.createScaledBitmap(tile2, sizeOfTile, sizeOfTile, true);

        //create starting place sprites
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

        //create room sprite sprites
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

        //creates board and assigns tiles

        //the board goes:  edge: 0, edge: 1, edge: 2, ... edge: 22
        //                 edge: 23, tile 24:, tile 25, ... tile: 43, edge: 44
        //                                  continue for 22 rows ...
        //                               end the board with a row of edges

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                //create border
                if (j == 0 | i == 0 | i == (h - 1) | j == (w - 1)) {
                    arrBoard.add(new Tile(edge, j * sizeOfTile + Constraints.SCREEN_WIDTH / 2 - (w / 2) * sizeOfTile,
                            i * sizeOfTile + 500 * Constraints.SCREEN_HEIGHT / 1920, sizeOfTile, sizeOfTile));
                }
                //create dark tiles
                else if ((i + j) % 2 == 0) {
                    arrBoard.add(new Tile(tile1, j * sizeOfTile + Constraints.SCREEN_WIDTH / 2 - (w / 2) * sizeOfTile,
                            i * sizeOfTile + 500 * Constraints.SCREEN_HEIGHT / 1920, sizeOfTile, sizeOfTile));
                }
                //create creme tiles
                else {
                    arrBoard.add(new Tile(tile2, j * sizeOfTile + Constraints.SCREEN_WIDTH / 2 - (w / 2) * sizeOfTile,
                            i * sizeOfTile + 500 * Constraints.SCREEN_HEIGHT / 1920, sizeOfTile, sizeOfTile));
                }
            }
        }

        //set up players and player pieces based on how many players
        //places characters on respected starting tile
        //**NOTE: always sets the first turn to player 1
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

        // invalidate tells the draw method to redraw everything
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
        //idk why the tutorial told me to put a postDelayed but it works so I'm not complaining
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

    //for all movement functions:
    //subtract/add the number of moves by 1 or 22
    //set the player's placement on the board with x and y


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
        n--;
        System.out.println("********************** MoveDown updated n: " + GameView.n + "\n");
        turn.setPlacement(turn.getPlacement() + 22);
        turn.setX(GameView.arrBoard.get(turn.getPlacement()).getTileX() + 3);
        turn.setY(GameView.arrBoard.get(turn.getPlacement()).getTileY() + 3);

    }
}
