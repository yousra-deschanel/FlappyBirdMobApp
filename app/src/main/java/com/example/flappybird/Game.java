package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Game extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //to get the size of the screen
        Point point= new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView= new GameView(this, point.x, point.y);
        //show our methode view on the screen
        setContentView(gameView);


    }
}