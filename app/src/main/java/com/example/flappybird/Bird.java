package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bird {
    public boolean isGoingUp = false;
    int birdX, birdY, dWidth, dHeight , birdFrame = 0 ;;
    Bitmap[] birds;
    GameView gameView;

    Bird(GameView gameView, int screenY, Resources res){
        this.gameView = gameView;
        //setting up the birds imgs
        birds = new Bitmap[3];
        birds[0] = BitmapFactory.decodeResource(res,R.drawable.bird1);
        birds[1] = BitmapFactory.decodeResource(res,R.drawable.bird2);
        birds[2] = BitmapFactory.decodeResource(res,R.drawable.bird3);

        //initially birds position at the center of the screen
        birdX = dWidth/2- birds[0].getWidth()/2;
        birdY = dHeight/2- birds[0].getWidth()/2;
    }

    int getBird(){
        switch(birdFrame) {
            case 0:
                birdFrame = 1;
                break;
            case 1:
                birdFrame = 2;
                break;
            case 2:
                birdFrame = 0;
                break;
        }
        return birdFrame;
    }
}
