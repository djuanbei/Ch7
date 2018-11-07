package com.example.yunyun.ch7;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    String[] images =null;
    AssetManager assets=null;
    int currentImg=0;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView =(ImageView)  findViewById(R.id.imageView);

        try{
            assets=getAssets();
            images= assets.list("");

        }catch (Exception e){
            e.printStackTrace();
    }

        final Button next=(Button) findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentImg>=images.length){
                    currentImg=0;
                }
                while (!images[currentImg].endsWith(".png") &&! images[currentImg].endsWith(".jpg") && !images[currentImg].endsWith(".gij") ){
                    currentImg++;
                    if(currentImg>=images.length){
                        currentImg=0;
                    }
                }

                InputStream assetFile=null;
                try{

                    assetFile=assets.open(images[currentImg++]);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                /*
                BitmapDrawable  bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                if(bitmapDrawable!=null && !bitmapDrawable.getBitmap().isRecycled()){
                    bitmapDrawable.getBitmap().recycle();
                }*/

                imageView.setImageBitmap(BitmapFactory.decodeStream( assetFile));

            }
        });

    }
}
