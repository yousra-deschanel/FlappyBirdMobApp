package com.example.flappybird;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


import java.util.logging.LogRecord;

public class GameView extends View {
    //game variables:
    Handler handler;//Handler is required to schedule runnable after some delay
    Runnable runnable;
    final int UPDATE_MILLS = 30;
    Bitmap background;
    Display display;
    Point point;

    int dWidth, dHeight;//Device width and height respectively
    Rect rect ;
    //creating a bitmap array for the bird
    Bitmap[] birds;
    //we need an integer var to keep track of bird image/frame
    int birdFrame = 0 ;
    int velocity = 0, gravity = 3;
    //we need to keep track of the bird position
    int birdX, birdY;

    boolean gameState = false;
    int gap = 400; //gap between top pipe and bottom pipe






    public GameView(Context context){
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                invalidate();//this will call onDraw
            }
        };
        background = BitmapFactory.decodeResource(getResources(),R.drawable.nightbg);
        //adjust the background to the screen size
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);

        //setting up the birds imgs
        birds = new Bitmap[3];
        birds[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bird1);
        birds[1] = BitmapFactory.decodeResource(getResources(),R.drawable.bird2);
        birds[2] = BitmapFactory.decodeResource(getResources(),R.drawable.bird3);

        //initially birds position at the center of the screen
        birdX = dWidth/2- birds[0].getWidth()/2;
        birdY = dHeight/2- birds[0].getWidth()/2;
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //drawing the bg
        canvas.drawBitmap(background,null,rect,null);

        //sequencing the display of the 3 birds
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
        if(gameState) {
            //keeping the bird inside the screen borders
            if (birdY < (dHeight - birds[0].getHeight()) || velocity < 0) {
                //as the bird falls, it gets faster as the velocity value increments by gravity each time
                velocity += gravity;
                birdY += velocity;
            }
        }

        //displaying the birds
        canvas.drawBitmap(birds[birdFrame], birdX , birdY ,null);

        handler.postDelayed(runnable, UPDATE_MILLS);
    }
    //get the touch event
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){//detecting tap on the screen
            //here we want the bird to move up
            velocity = -30;
            gameState = true;
        }

        //by returning true indicates that we r done with touch event and no further action is required by android
        return true;
    }

}
