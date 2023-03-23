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
    hall;
    public static int sizeOfMap = 35 * Constraints.SCREEN_WIDTH/1000;
    private int h = 20, w = 20;
    private ArrayList<Board> arrBoard = new ArrayList<>();
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tile1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.dark);
        tile1 = Bitmap.createScaledBitmap(tile1, sizeOfMap, sizeOfMap, true);
        tile2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.light);
        tile2 = Bitmap.createScaledBitmap(tile2, sizeOfMap, sizeOfMap, true);

        study = BitmapFactory.decodeResource(this.getResources(), R.drawable.study);
        study = Bitmap.createScaledBitmap(study, 242, 205, true);
        library = BitmapFactory.decodeResource(this.getResources(), R.drawable.library);
        library = Bitmap.createScaledBitmap(library, 266, 230, true);
        billiard = BitmapFactory.decodeResource(this.getResources(), R.drawable.billiard_room);
        billiard = Bitmap.createScaledBitmap(billiard, 282, 174, true);
        conservatory = BitmapFactory.decodeResource(this.getResources(), R.drawable.conservatory);
        conservatory = Bitmap.createScaledBitmap(conservatory, 166, 205, true);
        hall = BitmapFactory.decodeResource(this.getResources(), R.drawable.hall);
        hall = Bitmap.createScaledBitmap(hall, 302, 212, true);

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
        for (int i = 0; i < arrBoard.size(); i++){
            canvas.drawBitmap(arrBoard.get(i).getBm(), arrBoard.get(i).getX(),
                    arrBoard.get(i).getY(), null);
        }

        canvas.drawBitmap(study, 156,546, null);
        canvas.drawBitmap(library, 149,678, null);
        canvas.drawBitmap(billiard, 158,911, null);
        canvas.drawBitmap(conservatory, 159,1101, null);
        canvas.drawBitmap(hall, 488,548, null);
    }
}
