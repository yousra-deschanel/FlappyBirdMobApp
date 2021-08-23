package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Set extends AppCompatActivity {

    private static String choice = "yellow";



    private static String bgchoice = "day";
    private static String pipechoice = "green";
    ImageView left, right, bird , back , greenleft, greenright, bg , yellowleft, yellowright, pipe ;
    int clicked = 0 ;

    public static String getChoice() {
        return choice;
    }
    public static String getBgchoice() { return bgchoice; }
    public static String getPipechoice() { return pipechoice; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        bird = findViewById(R.id.bird);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
        back = findViewById(R.id.back);

        bg = findViewById(R.id.bgsmall);
        greenright = findViewById(R.id.rightgreen);
        greenleft = findViewById(R.id.leftgreen);

        pipe = findViewById(R.id.pipe);
        yellowright = findViewById(R.id.rightyellow);
        yellowleft = findViewById(R.id.leftyellow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Set.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(clicked) {
                    case 0:
                        bird.setImageResource(R.drawable.redbird1);
                        clicked++;
                        choice = "red";
                        break;
                    case 1:
                        bird.setImageResource(R.drawable.bluebird1);
                        clicked++;
                        choice = "blue";
                        break;
                    case 2:
                        bird.setImageResource(R.drawable.bird1);
                        clicked = 0;
                        choice = "yellow";
                        break;
                }
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(clicked) {
                    case 0:
                        bird.setImageResource(R.drawable.bluebird1);
                        clicked++;
                        choice = "red";
                        break;
                    case 1:
                        bird.setImageResource(R.drawable.redbird1);
                        clicked++;
                        choice = "blue";
                        break;
                    case 2:
                        bird.setImageResource(R.drawable.bird1);
                        clicked = 0;
                        choice = "yellow";
                        break;
                }
            }
        });


        greenright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bgchoice == "day"){
                    bg.setImageResource(R.drawable.smallbgnight);
                    bgchoice = "night";
                }
                else{
                    bg.setImageResource(R.drawable.bgsmall);
                    bgchoice = "day";
                }
            }
        });
        greenleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bgchoice == "day"){
                    bg.setImageResource(R.drawable.smallbgnight);
                    bgchoice = "night";
                }
                else{
                    bg.setImageResource(R.drawable.bgsmall);
                    bgchoice = "day";
                }

            }
        });

        yellowright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pipechoice == "green"){
                    pipe.setImageResource(R.drawable.pipered);
                    pipechoice = "red";
                }
                else{
                    pipe.setImageResource(R.drawable.bottom_pipe);
                    pipechoice = "green";
                }
            }
        });
        yellowleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pipechoice == "green"){
                    pipe.setImageResource(R.drawable.pipered);
                    pipechoice = "red";
                }
                else{
                    pipe.setImageResource(R.drawable.bottom_pipe);
                    pipechoice = "green";
                }

            }
        });



    }
}