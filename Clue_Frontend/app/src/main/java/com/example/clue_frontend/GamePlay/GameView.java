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
import java.util.Arrays;
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

    static Bitmap scarlet;
    static Bitmap white;
    static Bitmap plum;
    static Bitmap mustard;
    static Bitmap green;
    static Bitmap peacock;

    public static int sizeOfMap = (35 * Constraints.SCREEN_WIDTH) / 1000;
    private int h = 22, w = 22;
    public static ArrayList<Tile> arrBoard = new ArrayList<>();

    //This is the boundary and door information for each room. Each tile is ordered as: Tile placement on board, up, down, left, right, door
    public static String[][] study_room_info = {{"28","right","study"}, {"45", "door","study"}, {"46", "down","study"}, {"47", "down","study"}, {"48", "down","study"},
            {"50", "right","study"}, {"71", "left","study"}, {"72", "right","study"}, {"120", "left", "door","study"}, {"121", "down", "right","study"}};
    public static String[][] library_room_info = {{"111", "up","library"},{"112", "door","library"},{"113", "up", "right","library"},{"135","right","library"},
            {"158", "up","library"},{"159", "up","library"},{"160", "up","right","library"},{"182", "door","library"},{"199", "down","library"},
            {"200", "down","library"},{"201", "down","library"},{"202", "down","library"},{"203", "down","library"},{"204", "down", "right","library"}};
    public static String[][] billiard_room_info = {{"243","door","billiard"},{"244", "up","billiard"},{"245", "up","billiard"},{"246", "up","billiard"}, {"247", "up","billiard"},
            {"248", "up","billiard"},{"249", "up","right","billiard"},{"268", "down","billiard"},{"269", "down","billiard"},{"270", "down","billiard"},
            {"271", "down","right","billiard"}, {"289", "door","billiard"}, {"309", "down","billiard"},{"310", "down","billiard"},{"311", "down","right","billiard"}};
    public static String[][] conservatory_room_info = {{"353","up","conservatory"},{"354","up","conservatory"},{"355","up","door","conservatory"},
            {"378","up","right","conservatory"}, {"400","right","conservatory"},{"422","right","conservatory"},{"444","right","conservatory"}};
    public static String[][] hall_room_info = {{"30","left","hall"},{"35","right","hall"},{"52","door","hall"},{"57","door","hall"},{"74","left","hall"},{"79","right","hall"},
            {"96","left","hall"},{"101","right","hall"},{"118","left","down","hall"},{"119","down","hall"},{"120","door","hall"},{"121","door","hall"},{"122","down","hall"},
            {"123","down","right","hall"}};

    public static String[][] ball_room_info = {{"313","up","left","ball"},{"314","up","ball"},{"315","up","ball"},{"316","up","ball"},{"317","up","ball"},
            {"318","door","ball"}, {"319","door","ball"},{"320","up","ball"},{"321","up","ball"},{"322","up","right","ball"},{"335","down","left","ball"},
            {"336","down"},{"344","door"},{"359","left"},{"366","right"}, {"381","door","ball"},{"388","right","ball"}, {"403","left","ball"}, {"410","right","ball"},
            {"425","down","left","ball"},{"426","down","ball"},{"427","door","ball"},{"430","door","ball"},{"431","down","ball"},
            {"432","down","right","ball"},{"450","down","left","ball"},{"451","down","right","ball"}};

    public static String[][] lounge_room_info = {{"37","door","lounge"},{"59","left","lounge"},{"81","down","left","lounge"}, {"104","left","lounge"},
            {"126","left","down","lounge"}, {"127","door","lounge"},{"128","down","lounge"}, {"129","down","lounge"},{"130","down","lounge"}};

    public static String[][] dinning_room_info = {{"190","up","dinning"},{"191","up","dinning"},{"192","up","dinning"},{"193","up","dinning"},{"194","up","dinning"},
            {"195","down","dinning"},{"196","up","dinning"},{"212","left","dinning"}, {"234","left","dinning"},{"256","left","dinning"},{"278","down","left","dinning"},
            {"279","down","dinning"},{"280","down","dinning"},{"303","down","door","dinning"},{"304","down","dinning"},{"305","down","dinning"},{"306","down","dinning"}};
    public static String[][] kitchen_room_info = {{"368","up","left","kitchen"},{"369","down","kitchen"},{"370","up","kitchen"},{"371","up","kitchen"},{"372","up","kitchen"},
            {"390","left","kitchen"},{"412","left","kitchen"}, {"434","left","kitchen"},{"456","left","kitchen"}};


    public static String[][] clue_info = {{"163","clue"},{"164","clue"},{"165","clue"},{"166","clue"},{"185","clue"},{"188","clue"},{"207","clue"},
            {"210","clue"},{"229","clue"},{"232","clue"},{"251","clue"},{"254","clue"},{"273","clue"},{"274","clue"},{"275","clue"},{"276","clue"}};

    //I created a room class to make data retrieval easier
    public static Room study_room, library_room, billiard_room, conservatory_room, hall_room, ball_room,
            lounge_room, dinning_room, kitchen_room, clue_room;

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
        clue_room = new Room(clue_info);

        total_rooms.add(study_room);
        total_rooms.add(library_room);
        total_rooms.add(billiard_room);
        total_rooms.add(conservatory_room);
        total_rooms.add(hall_room);
        total_rooms.add(ball_room);
        total_rooms.add(lounge_room);
        total_rooms.add(dinning_room);
        total_rooms.add(kitchen_room);
        total_rooms.add(clue_room);

//        System.out.println("************* moves:" + moves + "\n");



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

        scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet);
        scarlet = Bitmap.createScaledBitmap(scarlet, 31, 30, true);
        white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white_player);
        white = Bitmap.createScaledBitmap(white, 30, 30, true);
        plum = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum);
        plum = Bitmap.createScaledBitmap(plum, 30, 30, true);
        mustard = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard);
        mustard = Bitmap.createScaledBitmap(mustard, 34, 30, true);
        green = BitmapFactory.decodeResource(this.getResources(), R.drawable.green);
        green = Bitmap.createScaledBitmap(green, 30, 30, true);
        peacock = BitmapFactory.decodeResource(this.getResources(), R.drawable.peacock);
        peacock = Bitmap.createScaledBitmap(peacock, 30, 30, true);


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


        player = new Player(GameView.scarlet, 468, 0, 0);
        player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
        player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
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
                    if(element.getRoom()[i][element.getRoom()[i].length - 2].equals("right")){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                    //if going into the center
                    GiveRoom(i,element);
                }

            }
            if(border){
                break;
            }
        }

        //After the scan of all the room's border's, if there was no border detected, then
        //it's ok to move there
        if(!border){
//

            moves--;
            player.setPlacement(player.getPlacement() - 1);
            player.setX(GameView.arrBoard.get(player.getPlacement()).getTileX() + 3);
            player.setY(GameView.arrBoard.get(player.getPlacement()).getTileY() + 3);
        }
//        System.out.println("---------------------------------------- \n\n");
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
                    if(element.getRoom()[i][element.getRoom()[i].length-2]=="left"){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                    GiveRoom(i,element);
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
//        System.out.println("---------------------------------------- \n\n");
    }

    public static void MoveUp() {
        //Boolean if the player is going into a wall
        boolean border = false;
        //Check each room
        for (Room element : total_rooms) {
            for (int i = 0; i <= element.getRoom().length-1; i++) {


//                System.out.println("element.getRoom()[i][1]: " + element.getRoom()[i][1]);

                //If the next move placement matches any of the border placement
                if (String.valueOf(player.getPlacement() - 22).equals(element.getRoom()[i][0])) {
                    //If there's a border moving left
                    if(element.getRoom()[i][element.getRoom()[i].length-2]=="down"){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                    GiveRoom(i,element);
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
//        System.out.println("---------------------------------------- \n\n");

    }

    public static void MoveDown() {
        //Boolean if the player is going into a wall
        boolean border = false;
        //Check each room
        for (Room element : total_rooms) {
            for (int i = 0; i <= element.getRoom().length-1; i++) {
                //If the next move placement matches any of the border placement
                if (String.valueOf(player.getPlacement() + 22).equals(element.getRoom()[i][0])) {
                    //If there's a border moving left
                    if(element.getRoom()[i][element.getRoom()[i].length-2]=="up"){
                        //There is a border there that the player can't move through
                        border = true;
                        break;
                    }
                    GiveRoom(i,element);
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
//        System.out.println("---------------------------------------- \n\n");
    }

    public static String GiveRoom(int i, Room element){
        if(element.getRoom()[i][1] == "clue"){
            Game.makeFinalGuess("clue");
        }else if (element.getRoom()[i][1] == "study") {
            Game.makeAGuess("study");
            return "study";
        }else if (element.getRoom()[i][1] == "library") {
            Game.makeAGuess("library");
            return "library";
        }else if (element.getRoom()[i][1] == "billiard") {
            Game.makeAGuess("billiard");
            return "billiard";
        }else if (element.getRoom()[i][1] == "conservatory") {
            Game.makeAGuess("conservatory");
            return "conservatory";
        }else if (element.getRoom()[i][1] == "hall") {
            Game.makeAGuess("hall");
            return "hall";
        }else if (element.getRoom()[i][1] == "ball") {
            Game.makeAGuess("ball");
            return "ball";
        }else if (element.getRoom()[i][1] == "lounge") {
            Game.makeAGuess("lounge");
            return "lounge";
        }else if (element.getRoom()[i][1] == "dinning") {
            Game.makeAGuess("dinning");
            return "dinning";
        }else if (element.getRoom()[i][1] == "kitchen") {
            Game.makeAGuess("kitchen");
            return "kitchen";
        }
        return "no room";
    }
}
