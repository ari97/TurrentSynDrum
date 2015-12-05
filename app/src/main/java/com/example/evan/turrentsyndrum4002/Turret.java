package com.example.evan.turretsyndrum4002;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Evan on 12/5/2015.
 */
public class Turret extends GameObject {

    private Bitmap image;
    private double turretPos;
    private double targetPos;
    private int health;
    private int score;
    private double speed;
    private double acceleration;
    private boolean joyStickUp;
    private boolean alive;
    private boolean shooting;
    private long startTime;


    public Turret(Bitmap res, int x, int y, double a){
        this.x=x;
        this.y=y;
        acceleration=a;
        speed=0;
        score=0;
        health=0;
        turretPos=0;
        targetPos=0;
    }

    public void shoot(){

    }

    public void setShooting(boolean b){
        shooting=b;
    }

    public void setTargetPos(double t){
        targetPos=t;
    }

    public void setJoyStickUp(boolean b){
        joyStickUp=b;
    }

    public void update()
    {
        long elapsed=System.nanoTime()-startTime/1000000000;
        if(elapsed>1000){
            score++;
            startTime=System.nanoTime();
        }

        if(joyStickUp) {
            //clockwise
            if (targetPos < turretPos) {
                speed += acceleration;
            } else {
                speed -= acceleration;
            }

            if (speed >= 5) {
                speed = 5;
            }
            turretPos += speed;
        }else{
            speed=0;
            targetPos=turretPos;
        }

    }

    public void draw(Canvas canvas){
        final int savesState=canvas.save();
        canvas.rotate((float)turretPos);
        canvas.drawBitmap(image, 0, 0,null);
        canvas.restoreToCount(savesState);
    }
}
