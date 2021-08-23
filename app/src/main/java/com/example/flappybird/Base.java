package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Base {
    int x,y ;
    Bitmap base;
    GameView gameView;

    Base(GameView gameView,int dWidth, int dHeight , Resources res){
        this.gameView = gameView;

        base = BitmapFactory.decodeResource(res,R.drawable.base);

        //resize this base to fit on the screen
        base= Bitmap.createScaledBitmap(base, dWidth, base.getHeight(), false);

        //base position at the bottom of the screen
        x = dWidth/2 - base.getWidth()/2;
        y = dHeight - base.getHeight()/2;


    }

    Bitmap getBase(){
        return base ;
    }
}
