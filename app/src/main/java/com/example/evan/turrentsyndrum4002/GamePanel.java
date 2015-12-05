package com.example.evan.turretsyndrum4002;

import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;




public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 580;
    public static final int HEIGHT = 386;
    private MainThread thread;
    private Background bg;
    private Turret turret;
    //private Joystick joystick;

    public GamePanel(Context context)    {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;

            }catch(InterruptedException e){e.printStackTrace();}

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        bg=new Background(BitmapFactory.decodeResource(getResources(), R.drawable.gamebackgroundspace));
        turret=new Turret(BitmapFactory.decodeResource(getResources(), R.drawable.turret), getHeight()/2, getWidth()/2, 0.3);
        // need to sub ball.getWidth()/2 and ball.getHight()/2 form stuff
        //joystick=new Joystick(BitmapFactory.decodeResource(getResources(), R.drawable.joystickfront),
        //        BitmapFactory.decodeResource(getResources(), R.drawable.joystickback), (getWidth()*3/4),getHeight()/4 );

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //if(event.)
        return super.onTouchEvent(event);
    }

    public void update(){
        turret.update();
    }

    @Override
    public void draw(Canvas canvas){
        final float SCALE_X=getWidth()/(WIDTH*1.f);
        final float SCALE_Y=getHeight()/(HEIGHT*1.f);
        if(canvas!=null){
            final int savesState=canvas.save();
            canvas.scale(SCALE_X,SCALE_Y);
            bg.draw(canvas);
            turret.draw(canvas);
            canvas.restoreToCount(savesState);
        }


    }
}
