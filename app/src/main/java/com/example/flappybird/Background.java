package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Background {
    int x=0,y=0  ;
    Bitmap background;

    String bgchoice = Set.getBgchoice();

    Background(int screenX, int screenY,Resources res){

        if(bgchoice == "day"){
            background = BitmapFactory.decodeResource(res,R.drawable.bg);
        }
        if(bgchoice == "night"){
            background = BitmapFactory.decodeResource(res,R.drawable.bgnight);
        }


        //now we will resize this background to fit on the screen
        background= Bitmap.createScaledBitmap(background, screenX, screenY, false);



    }
    Bitmap getBackground(){

        return background;
    }
    public int getWidth() {
        return background.getWidth();
    }



}
