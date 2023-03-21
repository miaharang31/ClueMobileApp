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
    private Bitmap bm1, bm2;
    public static int sizeOfMap = 35 * Constraints.SCREEN_WIDTH/1000;
    private int h = 20, w = 20;
    private ArrayList<Board> arrBoard = new ArrayList<>();
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bm1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.dark);
        bm1 = Bitmap.createScaledBitmap(bm1, sizeOfMap, sizeOfMap, true);
        bm2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.light);
        bm2 = Bitmap.createScaledBitmap(bm2, sizeOfMap, sizeOfMap, true);
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if((i+j)%2 ==0){
                    arrBoard.add(new Board(bm1,j*sizeOfMap + Constraints.SCREEN_WIDTH/2-(w/2)*sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }else{
                    arrBoard.add(new Board(bm2,j*sizeOfMap + Constraints.SCREEN_WIDTH/2-(w/2)*sizeOfMap,
                            i * sizeOfMap + 500 * Constraints.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFFFFFF);
        for (int i = 0; i < arrBoard.size(); i++){
            canvas.drawBitmap(arrBoard.get(i).getBm(), arrBoard.get(i).getX(),
                    arrBoard.get(i).getY(), null);
        }
    }
}
