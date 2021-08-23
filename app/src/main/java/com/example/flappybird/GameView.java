package com.example.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;
import java.util.Random;
import java.util.logging.LogRecord;

public class GameView extends View {
    //game variables:
    Handler handler;//Handler is required to schedule runnable after some delay
    Runnable runnable;
    final int UPDATE_MILLS = 30;
    Display display;
    Point point;
    int dWidth, dHeight;//Device width and height respectively
    Rect rect ;
    int velocity = 0, gravity = 3;
    boolean gameState = false;
    int gap = 400; //gap between top pipe and bottom pipe
    Paint paint ;
    //declaring game objects
    Bird bird ;
    Background background;
    Base base1, base2;

    Bitmap menuIcon ;

    Random random;
    //creating an array of pipes
    private ArrayList<Pipe> arrPipes ;
    private int sumpipe, distance;

    String pipechoice = Set.getPipechoice() ;

    public GameView(Context context, int screenX, int screenY){
        super(context);
        paint = new Paint();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                update ();
                invalidate();//this will call onDraw
            }
        };

        //adjust the background to the screen size
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);

        background = new Background(dWidth, dHeight, getResources());
        bird = new Bird(this,dWidth, dHeight, getResources());

        base1=new Base(this,dWidth, dHeight, getResources());
        base2=new Base(this,dWidth, dHeight, getResources());

        base2.x= dWidth;

        initPipe();

        random = new Random();

        menuIcon = BitmapFactory.decodeResource(getResources(),R.drawable.menuicon);

    }


    private void initPipe(){
        sumpipe = 6 ;
        distance = 400;
        arrPipes = new ArrayList<>();
        for(int i=0 ; i < sumpipe ; i++){

            if(i < sumpipe/2 ){
                //initialize the initial position of the water pipes:
                this.arrPipes.add(new Pipe(
                        dWidth+i*((dWidth+ 250*dWidth/1000)/(sumpipe/2)),
                        0,
                        200*dWidth/1000,
                        dHeight/2)
                );

                if(pipechoice == "green"){
                    this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.top_pipe));
                }
                if(pipechoice == "red"){
                    this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.toppipered));
                }

                this.arrPipes.get(this.arrPipes.size()-1).randomY();

            }else{

                this.arrPipes.add(new Pipe(
                        this.arrPipes.get(i-sumpipe/2).getX(),
                        this.arrPipes.get(i-sumpipe/2).getY() +this.arrPipes.get(i-sumpipe/2).getHeight() + this.distance,
                        200 * dWidth/1000,
                        dHeight/2)
                );


                if(pipechoice == "green"){
                    this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.bottom_pipe));

                }
                if(pipechoice == "red"){
                    this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipered));

                }
            }
        }
    }
    public void update (){
        //we move both bases by 10 pixels
        base1.x -= 10 ;
        base2.x -= 10;

        //when the base get to border left we reput it on the right
        if(base1.x + base1.getBase().getWidth() <0 ){
            base1.x = dWidth;
        }
        if(base2.x + base2.getBase().getWidth() <0 ){
            base2.x= dWidth;
        }

        //for each bird loop


    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //drawing the bg
        canvas.drawBitmap(background.getBackground(),null,rect,null);

        //displaying pipes
        int i = 0;
        for(Pipe pipe : arrPipes){
            if(this.arrPipes.get(i).getX() < -arrPipes.get(i).getWidth()){

                this.arrPipes.get(i).setX(dWidth);

                if(i < sumpipe/2 ){
                    arrPipes.get(i).randomY();

                }else{
                    arrPipes.get(i).setY(this.arrPipes.get(i-sumpipe/2).getY()
                            +this.arrPipes.get(i-sumpipe/2).getHeight() + this.distance);

                //if pipe comes out of the screen, reset position
                }
            }
            this.arrPipes.get(i).draw(canvas);
            i ++ ;
        }


        //drawing the base animation
        canvas.drawBitmap(base1.getBase(), base1.x, base1.y,paint);
        canvas.drawBitmap(base2.getBase(), base2.x, base2.y,paint);

        if(gameState) {
            //keeping the bird inside the screen borders
            if (bird.birdY < (dHeight-base1.getBase().getHeight()/2 - bird.getHeight()) || velocity < 0) {
                //as the bird falls, it gets faster as the velocity value increments by gravity each time
                velocity += gravity;
                bird.birdY += velocity;
            }
        }

        //displaying the birds
        canvas.drawBitmap(bird.getBird(), bird.birdX , bird.birdY ,null);

        //displaying menu icon
        //canvas.drawBitmap(menuIcon , 0 , 0 ,null);

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
