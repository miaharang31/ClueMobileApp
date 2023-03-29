package com.example.clue_frontend;

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
    hall, clue, ball, lounge, dinning, kitchen, scarlet_start, white_start, plum_start,
    mustard_start, green_start, peacock_start, scarlet, up;
    public static int sizeOfMap = 35 * Constraints.SCREEN_WIDTH/1000;
    private int h = 20, w = 20;
    private ArrayList<Tile> arrBoard = new ArrayList<>();
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tile1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.dark);
        tile1 = Bitmap.createScaledBitmap(tile1, sizeOfMap, sizeOfMap, true);
        tile2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.light);
        tile2 = Bitmap.createScaledBitmap(tile2, sizeOfMap, sizeOfMap, true);

        scarlet_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet_start);
        scarlet_start = Bitmap.createScaledBitmap(scarlet_start, 62, 60, true);
        white_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.white_start);
        white_start = Bitmap.createScaledBitmap(white_start, 57, 57, true);
        plum_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.plum_start);
        plum_start = Bitmap.createScaledBitmap(plum_start, 49, 49, true);
        mustard_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.mustard_start);
        mustard_start = Bitmap.createScaledBitmap(mustard_start, 50, 50, true);
        green_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.green_start);
        green_start = Bitmap.createScaledBitmap(green_start, 51, 51, true);
        peacock_start = BitmapFactory.decodeResource(this.getResources(), R.drawable.peacock_start);
        peacock_start = Bitmap.createScaledBitmap(peacock_start, 51, 51, true);

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

        scarlet = BitmapFactory.decodeResource(this.getResources(), R.drawable.scarlet);
        scarlet = Bitmap.createScaledBitmap(scarlet, 30, 30, true);

        up = BitmapFactory.decodeResource(this.getResources(), R.drawable.up_icon);
        up = Bitmap.createScaledBitmap(up, 45, 45, true);

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if((i+j)%2 ==0){
                    arrBoard.add(new Tile(tile1,j*sizeOfMap + Constraints.SCREEN_WIDTH/2-(w/2)*sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }else{
                    arrBoard.add(new Tile(tile2,j*sizeOfMap + Constraints.SCREEN_WIDTH/2-(w/2)*sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(scarlet_start, 343,1280, null);
        canvas.drawBitmap(white_start, 640,1270, null);
        canvas.drawBitmap(plum_start, 144, 1070, null);
        canvas.drawBitmap(mustard_start, 885, 1069, null);
        canvas.drawBitmap(green_start, 645, 529, null);
        canvas.drawBitmap(peacock_start, 384, 531, null);

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

        canvas.drawBitmap(scarlet, 358,1295, null);

        //canvas.drawBitmap(up, 350,1255, null);
    }
}
