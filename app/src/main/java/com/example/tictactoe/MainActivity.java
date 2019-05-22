package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int isUserActive = 0;

    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2 };
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                                {0,3,6}, {1,4,7}, {2,5,8},
                                {0,4,8}, {2,4,6}};

    public void dropIn (View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationX(-2000);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        gameState[tappedCounter] = isUserActive;


        if(isUserActive==0) {
            counter.setImageResource(R.drawable.green);
            isUserActive=1;

        }
        else{
            counter.setImageResource(R.drawable.red);
            isUserActive=0;
        }



        counter.animate().translationXBy(2000).setDuration(500);

        for(int[] winningPosition : winningPositions){
                 if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]]==gameState[winningPosition[2] ]&&
                    gameState[winningPosition[2]]!=2){

                     String winner ="";

                     if(isUserActive==0){
                         winner="red";
                     }
                     else{
                         winner="green";
                     }

                     Toast.makeText(this, winner+" has won", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
