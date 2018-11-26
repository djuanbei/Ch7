package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences=getSharedPreferences("test",MODE_PRIVATE);

        editor=preferences.edit();


    }

    public  void read(View view){
        String name=preferences.getString("time",null);

        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

    }
    public  void write(View view){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy 年 MM 月 dd 日 "+"hh:mm:ss");

        editor.putString("time", sdf.format(new Date()));
        editor.commit();

    }
}
