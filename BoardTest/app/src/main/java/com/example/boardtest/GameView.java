package com.example.boardtest;

import static com.example.boardtest.Constants.*;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread thread;
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new DrawThread(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 9; j++){
                canvas.drawLine(0,drawY + (i * cellWidth),cellWidth * 10,
                        drawY+ (i* cellWidth),p);
                canvas.drawLine(j * cellWidth, drawY, j * cellWidth, drawY + cellWidth * 9, p);
            }
        }

    }
}
