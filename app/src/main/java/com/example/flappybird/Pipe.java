package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.provider.SyncStateContract;

import java.util.Random;

public class Pipe extends Object {
    public static int speed ;


    public Pipe(float x, float y, int width, int height) {
        super(x, y, width, height);
        speed = 12  ;
    }
    public void draw(Canvas canvas){
        this.x -= speed;
        canvas.drawBitmap(this.bm, this.x, this.y , null);
    }

    public void randomY(){
        Random r = new Random();
        this.y = r.nextInt((0+this.height/4)+1) - this.height/4;
    }

    public void setBm(Bitmap bm){
        this.bm = Bitmap.createScaledBitmap(bm, width, height, true);
    }
}
