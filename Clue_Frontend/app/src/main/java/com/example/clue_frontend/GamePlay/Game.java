package com.example.clue_frontend.GamePlay;

import static com.example.clue_frontend.GamePlay.GameView.turn;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clue_frontend.MainActivity;
import com.example.clue_frontend.R;


public class Game extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //No idea what this stuff does. Blame the tutorial
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Sets the board up based on device's screen
        Constraints.SCREEN_WIDTH = dm.widthPixels;
        Constraints.SCREEN_HEIGHT = dm.heightPixels;
        View relativeLayout = findViewById(R.id.relative_layout);
        setContentView(R.layout.board);

        //Sets up the swipe lister



    }
}


