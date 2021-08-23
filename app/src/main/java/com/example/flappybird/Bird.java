package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.flappybird.Set;

import java.math.BigInteger;

public class Bird {
    public boolean isGoingUp = false;
    int birdX, birdY, dWidth, dHeight , birdFrame = 0 ;;
    Bitmap[] birds;
    GameView gameView;

    String choice = Set.getChoice();

    Bird(GameView gameView,int dWidth, int dHeight , Resources res){
        this.gameView = gameView;

        //setting up the birds imgs
        birds = new Bitmap[3];

        switch(choice) {
            case "yellow":
                birds[0] = BitmapFactory.decodeResource(res,R.drawable.bird1);
                birds[1] = BitmapFactory.decodeResource(res,R.drawable.bird2);
                birds[2] = BitmapFactory.decodeResource(res,R.drawable.bird3);
                break;
            case "red":
                birds[0] = BitmapFactory.decodeResource(res,R.drawable.redbird1);
                birds[1] = BitmapFactory.decodeResource(res,R.drawable.redbird2);
                birds[2] = BitmapFactory.decodeResource(res,R.drawable.redbird3);
                break;
            case "blue":
                birds[0] = BitmapFactory.decodeResource(res,R.drawable.bluebird1);
                birds[1] = BitmapFactory.decodeResource(res,R.drawable.bluebird2);
                birds[2] = BitmapFactory.decodeResource(res,R.drawable.bluebird3);
                break;
        }


        //initially birds position at the center of the screen
        birdX = dWidth/2- birds[0].getWidth()/2;
        birdY = dHeight/2- birds[0].getWidth()/2;
    }

    Bitmap getBird(){
        //sequencing the display of the 3 birds
        if(birdFrame == 0) {
            birdFrame ++;
            return birds[0];
        }
        if(birdFrame == 1) {
            birdFrame ++;
            return birds[1];
        }
        birdFrame = 0;
        return birds[2];
    }

    public int getHeight() {
        return birds[0].getHeight();
    }
}
