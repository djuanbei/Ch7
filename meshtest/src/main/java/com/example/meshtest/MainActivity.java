package com.example.meshtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Bitmap  bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this, R.drawable.ta));
    }

    private  class MyView extends View {
        private final  int WIDTH=20;
        private final int HEIGHT=20;

        private final int COUNT=(WIDTH +1) *(HEIGHT+1);

        private final float[] verts=new float[COUNT*2];

        private final float[] orig=new float[COUNT*2];

        public  MyView(Context  context, int drawabledId){
            super(context);
            setFocusable(true);
            bitmap= BitmapFactory.decodeResource(getResources(), drawabledId);


            float bitmapWidth=bitmap.getWidth();
            float bitmapHeight=bitmap.getHeight();

            int index=0;
            for(int y=0; y<= HEIGHT; y++){
                float fy=bitmapHeight *y/HEIGHT;
                for(int x=0; x<= WIDTH; x++){
                    float fx=bitmapWidth*x/WIDTH;

                    orig[index*2]=verts[index*2]=fx;
                    orig[index*2+1]=verts[index*2+1]=fy;
                    index++;

                }
            }

            setBackgroundColor(Color.WHITE);

        }

        @Override
        protected  void onDraw(Canvas canvas){
            canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null  );
        }

        private void warp(float cx, float cy){
            for(int i=0; i< COUNT*2; i+=2){
                float dx=cx-orig[i+0];
                float dy=cy-orig[i+1];
                float dd=dx*dx+dy*dy;


                float d=(float) Math.sqrt(dd);

                float pull =8000/((float)(dd*d));

                if(pull >=1){
                    verts[i]=cx;
                    verts[i+1]=cy;
                }else{
                    verts[i]=orig[i]+dx*pull;
                    verts[i+1]=orig[i+1]+dy*pull;
                }
            }
            /**
             *  draw
             */
            invalidate();
        }


        @Override
        public  boolean onTouchEvent(MotionEvent event){
            warp(event.getX(), event.getY());
            return  true;
        }



    }
}
