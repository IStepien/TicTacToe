package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean isUserActive;

    public void dropIn (View view){
        ImageView counter = (ImageView) view;

        counter.setTranslationX(-2000);
        if(isUserActive) {
            counter.setImageResource(R.drawable.green);
            isUserActive=false;
        }
        else{
            counter.setImageResource(R.drawable.red);
            isUserActive=true;
        }
        counter.animate().translationXBy(2000).setDuration(500);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
