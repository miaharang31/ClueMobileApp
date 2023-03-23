package com.example.boardtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {
    private Bitmap tile1, tile2, study, library, billiard, conservatory,
    hall, clue, ball, lounge, dinning, kitchen, scarlet, white, plum,
    mustard, green, peacock;
    public static int sizeOfMap = 35 * Constraints.SCREEN_WIDTH/1000;
    private int h = 20, w = 20;
    private ArrayList<Board> arrBoard = new ArrayList<>();
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tile1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.dark);
        tile1 = Bitmap.createScaledBitmap(tile1, sizeOfMap, sizeOfMap, true);
        tile2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.light);
        tile2 = Bitmap.createScaledBitmap(tile2, sizeOfMap, sizeOfMap, true);

        scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet_start);
        scarlet = Bitmap.createScaledBitmap(scarlet, 55, 50, true);
        white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white_start);
        white = Bitmap.createScaledBitmap(white, 57, 57, true);
        plum = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum_start);
        plum = Bitmap.createScaledBitmap(plum, 49, 49, true);
        mustard = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard_start);
        mustard = Bitmap.createScaledBitmap(mustard, 50, 50, true);
        green = BitmapFactory.decodeResource(this.getResources(), R.drawable.green_start);
        green = Bitmap.createScaledBitmap(green, 51, 51, true);
        peacock = BitmapFactory.decodeResource(this.getResources(), R.drawable.peacock_start);
        peacock = Bitmap.createScaledBitmap(peacock, 51, 51, true);

        study = BitmapFactory.decodeResource(this.getResources(), R.drawable.study);
        study = Bitmap.createScaledBitmap(study, 242, 205, true);
        library = BitmapFactory.decodeResource(this.getResources(), R.drawable.library);
        library = Bitmap.createScaledBitmap(library, 266, 230, true);
        billiard = BitmapFactory.decodeResource(this.getResources(), R.drawable.billiard_room);
        billiard = Bitmap.createScaledBitmap(billiard, 282, 174, true);
        conservatory = BitmapFactory.decodeResource(this.getResources(), R.drawable.conservatory);
        conservatory = Bitmap.createScaledBitmap(conservatory, 166, 205, true);
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

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if((i+j)%2 ==0){
                    arrBoard.add(new Board(tile1,j*sizeOfMap + Constraints.SCREEN_WIDTH/2-(w/2)*sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }else{
                    arrBoard.add(new Board(tile2,j*sizeOfMap + Constraints.SCREEN_WIDTH/2-(w/2)*sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(scarlet, 346,1270, null);
        canvas.drawBitmap(white, 640,1270, null);
        canvas.drawBitmap(plum, 144, 1070, null);
        canvas.drawBitmap(mustard, 885, 1069, null);
        canvas.drawBitmap(green, 645, 529, null);
        canvas.drawBitmap(peacock, 384, 531, null);

        for (int i = 0; i < arrBoard.size(); i++){
            canvas.drawBitmap(arrBoard.get(i).getBm(), arrBoard.get(i).getX(),
                    arrBoard.get(i).getY(), null);
        }

        canvas.drawBitmap(study, 156,546, null);
        canvas.drawBitmap(library, 149,678, null);
        canvas.drawBitmap(billiard, 158,911, null);
        canvas.drawBitmap(conservatory, 159,1101, null);
        canvas.drawBitmap(hall, 411,543, null);
        canvas.drawBitmap(clue, 461,770, null);
        canvas.drawBitmap(ball, 297,1018, null);
        canvas.drawBitmap(lounge, 676,546, null);
        canvas.drawBitmap(dinning, 627,792, null);
        canvas.drawBitmap(kitchen, 715,1098, null);
    }
}
