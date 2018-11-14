package com.example.fatpo;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer singer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button play=(Button) findViewById(R.id.play);
        Button stop=(Button) findViewById(R.id.stop);

        singer=MediaPlayer.create(this, R.raw.er);


        ImageView  imageView=(ImageView) findViewById(R.id.imageview);

        final AnimationDrawable anim=(AnimationDrawable) imageView.getBackground();


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.start();
                singer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.stop();
            }
        });


    }
}
