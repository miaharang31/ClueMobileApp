package com.example.clue_frontend.GamePlay;

import static com.example.clue_frontend.GamePlay.Constraints.SCREEN_HEIGHT;
import static com.example.clue_frontend.GamePlay.Constraints.SCREEN_WIDTH;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.example.clue_frontend.GamePlay.Player.Player;
import com.example.clue_frontend.GamePlay.DiceRoller;
import com.example.clue_frontend.R;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class GameView extends View {
    static Random rand = new Random();
    public static int moves = rand.nextInt(23) + 1;
    //public static int moves = DiceRoller.numSpaces;

    public static int number_of_players = 6;
    public static Bitmap edge;
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

    public static int sizeOfMap = (35 * Constraints.SCREEN_WIDTH) / 1000;
    private int h = 22, w = 22;
    public static ArrayList<Tile> arrBoard = new ArrayList<>();

    //This is the boundary and door information for each room. Each tile is ordered as: Tile placement on board, up, down, left, right, door
    public static String[][] study_room_info = {{"28","right"}, {"45", "door"}, {"46", "down"}, {"47", "down"}, {"48", "down"}, {"50", "right"}, {"71", "left"}, {"72", "right"},
            {"120", "left", "door"}, {"121", "down", "right"}};
    public static String[][] library_room_info = {{"111", "up"},{"112", "door"},{"113", "up", "right"},{"135","right"},
            {"158", "up"},{"159", "up"},{"160", "up","right"},{"182", "door"},{"199", "down"},
            {"200", "down"},{"201", "down"},{"202", "down"},{"203", "down"},{"204", "down", "right"}};
    public static String[][] billiard_room_info = {{"243","door"},{"244", "up"},{"245", "up"},{"246", "up"}, {"247", "up"},
            {"248", "up"},{"249", "up","right"},{"268", "down"},{"269", "down"},{"270", "down"},{"271", "down","right"},{"289", "door"},
            {"309", "down"},{"310", "down"},{"311", "down","right"}};
    public static String[][] conservatory_room_info = {{"353","up"},{"354","up"},{"355","up","door"},{"378","up","right"},
            {"400","right"},{"422","right"},{"444","right"}};
    public static String[][] hall_room_info = {{"30","left"},{"35","right"},{"52","door"},{"57","door"},{"74","left"},{"79","right"},
            {"96","left"},{"101","right"},{"118","left","down"},{"119","down"},{"120","door"},{"121","door"},{"122","down"},{"123","down","right"}};

    public static String[][] ball_room_info = {{"313","up","left"},{"314","up"},{"315","up"},{"316","up"},{"317","up"},{"318","door"},
            {"319","door"},{"320","up"},{"321","up"},{"322","up","right"},{"335","down","left"},{"336","down"},{"344","door"},{"359","left"},{"366","right"},
            {"381","door"},{"388","right"},{"403","left"},{"410","right"},{"425","down","left"},{"426","down"},{"427","door"},{"430","door"},{"431","down"},
            {"432","down","right"},{"450","down","left"},{"451","down","right"}};

    public static String[][] lounge_room_info = {{"37","door"},{"59","left"},{"81","down","left"},{"104","left"},{"126","left","down"},{"127","door"},{"128","down"},
            {"129","down"},{"130","down"}};

    public static String[][] dinning_room_info = {{"190","up","left"},{"191","up"},{"192","up"},{"193","up"},{"194","up"},{"195","down"},{"196","up"},{"212","left"},
            {"234","left"},{"256","left"},{"278","down","left"},{"279","down"},{"280","down"},{"303","down","door"},{"304","down"},{"305","down"},{"306","down"}};
    public static String[][] kitchen_room_info = {{"368","up","left"},{"369","down"},{"370","up"},{"371","up"},{"372","up"},{"390","left"},{"412","left"},
            {"434","left"},{"456","left"}};

    //I created a room class to make data retrieval easier
    public static Room study_room, library_room, billiard_room, conservatory_room, hall_room, ball_room,
            lounge_room, dinning_room, kitchen_room;

    //This array stores all the rooms and their information
    public static ArrayList<Room> total_rooms = new ArrayList<>();

    public static Player player;
    Handler handler;
    Runnable r;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //creates tiles for the boards
        edge = BitmapFactory.decodeResource(this.getResources(), R.drawable.empty);
        edge = Bitmap.createScaledBitmap(edge, sizeOfMap, sizeOfMap, true);
        tile1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.dark);
        tile1 = Bitmap.createScaledBitmap(tile1, sizeOfMap, sizeOfMap, true);
        tile2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.light);
        tile2 = Bitmap.createScaledBitmap(tile2, sizeOfMap, sizeOfMap, true);

        //create rooms
        study_room = new Room(study_room_info);
        library_room = new Room(library_room_info);
        billiard_room = new Room(billiard_room_info);
        conservatory_room = new Room(conservatory_room_info);
        hall_room = new Room(hall_room_info);
        ball_room = new Room(ball_room_info);
        lounge_room = new Room(lounge_room_info);
        dinning_room = new Room(dinning_room_info);
        kitchen_room = new Room(kitchen_room_info);

        total_rooms.add(study_room);
        total_rooms.add(library_room);
        total_rooms.add(billiard_room);
        total_rooms.add(conservatory_room);
        total_rooms.add(hall_room);
        total_rooms.add(ball_room);
        total_rooms.add(lounge_room);
        total_rooms.add(dinning_room);
        total_rooms.add(kitchen_room);

        System.out.println("************* moves:" + moves + "\n");



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
        billiard = Bitmap.createScaledBitmap(billiard, 282, 172, true);
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

        //assigns color based on what player chose
        if(Objects.equals(Game.characterSelected, "scarlet")){
            scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet);
            scarlet = Bitmap.createScaledBitmap(scarlet, 31, 30, true);

            player = new Player(scarlet, 468, 0, 0);
            player.setX(arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(arrBoard.get(player.getPlacement()).getTileY() + 3);

            System.out.println("Chose scarlet");
        }
        else if (Objects.equals(Game.characterSelected, "white")) {
            white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white_player);
            white = Bitmap.createScaledBitmap(white, 30, 30, true);

            player = new Player(white, 476, 0, 0);
            player.setX(arrBoard.get(player.getPlacement()).getTileX() + 5);
            player.setY(arrBoard.get(player.getPlacement()).getTileY() + 3);
            System.out.println("Chose white");
        }
        else if (Objects.equals(Game.characterSelected, "plum")) {
            plum = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum);
            plum = Bitmap.createScaledBitmap(plum, 30, 30, true);

            player = new Player(plum, 330, 0, 0);
            player.setX(arrBoard.get(player.getPlacement()).getTileX() + 4);
            player.setY(arrBoard.get(player.getPlacement()).getTileY() + 6);
            System.out.println("Chose plum");
        }
        else if (Objects.equals(Game.characterSelected, "mustard")) {
            mustard = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard);
            mustard = Bitmap.createScaledBitmap(mustard, 34, 30, true);

            player = new Player(mustard, 351, 0, 0);
            player.setX(arrBoard.get(player.getPlacement()).getTileX() + 1);
            player.setY(arrBoard.get(player.getPlacement()).getTileY() + 3);
            System.out.println("Chose mustard");
        }
        else if (Objects.equals(Game.characterSelected, "green")) {
            green = BitmapFactory.decodeResource(this.getResources(), R.drawable.green);
            green = Bitmap.createScaledBitmap(green, 30, 30, true);

            player = new Player(green, 14, 0, 0);
            player.setX(arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(arrBoard.get(player.getPlacement()).getTileY() + 3);
            System.out.println("Chose green");
        }
        else if (Objects.equals(Game.characterSelected, "peacock")) {
            peacock = BitmapFactory.decodeResource(this.getResources(), R.drawable.peacock);
            peacock = Bitmap.createScaledBitmap(peacock, 30, 30, true);

            player = new Player(peacock, 7, 0, 0);
            player.setX(arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(arrBoard.get(player.getPlacement()).getTileY() + 5);
            System.out.println("Chose peacock");
        }

        //creates board
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (j == 0 | i == 0 | i == (h - 1) | j == (w - 1)) {
                    arrBoard.add(new Tile(edge, j * sizeOfMap + SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            i * sizeOfMap + 200 * Constraints.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                } else if ((i + j) % 2 == 0) {
                    arrBoard.add(new Tile(tile1, j * sizeOfMap + SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            i * sizeOfMap + 200 * Constraints.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                } else {
                    arrBoard.add(new Tile(tile2, j * sizeOfMap + SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap,
                            i * sizeOfMap + 200 * Constraints.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                }
            }
        }

        white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white_player);
        white = Bitmap.createScaledBitmap(white, 30, 30, true);

        player = new Player(white, 476, 0, 0);
        player.setX(arrBoard.get(player.getPlacement()).getTileX() + 5);
        player.setY(arrBoard.get(player.getPlacement()).getTileY() + 3);

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
        canvas.drawBitmap(scarlet_start, 345, 996, null);
        canvas.drawBitmap(white_start, 645, 993, null);
        canvas.drawBitmap(plum_start, 130, 777, null);
        canvas.drawBitmap(mustard_start, 907, 773, null);
        canvas.drawBitmap(green_start, 646, 218, null);
        canvas.drawBitmap(peacock_start, 387, 220, null);

        //draws rooms
        canvas.drawBitmap(study, 156, 249, null);
        canvas.drawBitmap(library, 149, 381, null);
        canvas.drawBitmap(billiard, 158, 616, null);
        canvas.drawBitmap(conservatory, 160, 804, null);
        canvas.drawBitmap(hall, 411, 245, null);
        canvas.drawBitmap(clue, 461, 473, null);
        canvas.drawBitmap(ball, 297, 721, null);
        canvas.drawBitmap(lounge, 676, 248, null);
        canvas.drawBitmap(dinning, 627, 458, null);
        canvas.drawBitmap(kitchen, 715, 801, null);

// TODO: Set up chacter stuff.


        //draws player pieces
        canvas.drawBitmap(player.getBm(), player.getX(), player.getY(), null);

        handler.postDelayed(r, 100);
    }

    public static void TurnLeft() {
        //Boolean if the player is going into a wall
        boolean border = false;

        //Check each room
        for (Room element : total_rooms) {
            for (int i = 0; i <= element.getRoom().length-1; i++) {

                //If the next move placement matches any of the border placement
                if (String.valueOf(player.getPlacement() - 1).equals(element.getRoom()[i][0])) {
                    //If there's a border moving right
                    if(element.getRoom()[i][element.getRoom()[i].length - 1].equals("right")){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                }
            }
            if(border){
                break;
            }
        }

        //After the scan of all the room's border's, if there was no border detected, then
        //it's ok to move there
        if(!border){
            moves--;
            player.setPlacement(player.getPlacement() - 1);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        }
        System.out.println("---------------------------------------- \n\n");
    }

    public static void TurnRight() {
        //Boolean if the player is going into a wall
        boolean border = false;

        //Check each room
        for (Room element : total_rooms) {
            for (int i = 0; i <= element.getRoom().length-1; i++) {

                //If the next move placement matches any of the border placement
                if (String.valueOf(player.getPlacement() + 1).equals(element.getRoom()[i][0])) {
                    //If there's a border moving left
                    if(element.getRoom()[i][element.getRoom()[i].length-1]=="left"){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                }
            }
            if(border){
                break;
            }
        }
        //After the scan of all the room's border's, if there was no border detected, then
        //it's ok to move there
        if (!border){
            moves--;
            player.setPlacement(player.getPlacement() + 1);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);

        }
        System.out.println("---------------------------------------- \n\n");
    }

    public static void MoveUp() {
        System.out.println("Line 358, In Game class, in moveup");

        //Boolean if the player is going into a wall
        boolean border = false;
        //Check each room
        for (Room element : total_rooms) {
            for (int i = 0; i <= element.getRoom().length-1; i++) {
                //If the next move placement matches any of the border placement
                if (String.valueOf(player.getPlacement() - 22).equals(element.getRoom()[i][0])) {
                    //If there's a border moving down
                    if(element.getRoom()[i][1]=="down"){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                }
            }
            if(border){
                break;
            }
        }

        //After the scan of all the room's border's, if there was no border detected, then
        //it's ok to move there
        if(!border){
            moves--;
            System.out.println("new_moves: " + moves);
            System.out.println("before player placement: " + player.getPlacement());
            player.setPlacement(player.getPlacement() - 22);
            System.out.println("after player placement: " + player.getPlacement());
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        }
        System.out.println("---------------------------------------- \n\n");

    }

    public static void MoveDown() {
        //Boolean if the player is going into a wall
        boolean border = false;
        //Check each room
        for (Room element : total_rooms) {
            for (int i = 0; i <= element.getRoom().length-1; i++) {
                //If the next move placement matches any of the border placement
                if (String.valueOf(player.getPlacement() + 22).equals(element.getRoom()[i][0])) {
                    //If there's a border moving up
                    if(element.getRoom()[i][1]=="up"){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                }
            }
            if(border){
                break;
            }
        }
        //After the scan of all the room's border's, if there was no border detected, then
        //it's ok to move there
        if(!border){
            moves--;
            player.setPlacement(player.getPlacement() + 22);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        }
        System.out.println("---------------------------------------- \n\n");
    }

}
