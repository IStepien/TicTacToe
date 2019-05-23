package com.example.tictactoe;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    int isUserActive = 0;
    boolean someoneHasWon;
    MediaPlayer mediaPlayer;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && someoneHasWon == false) {

            gameState[tappedCounter] = isUserActive;
            counter.setTranslationX(-2000);


            if (isUserActive == 0) {
                counter.setImageResource(R.drawable.green);
                isUserActive = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                isUserActive = 0;
            }


            counter.animate().translationXBy(2000).setDuration(500);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[2]] != 2) {

                    someoneHasWon = true;

                    String winner = "";

                    if (isUserActive == 0) {
                        winner = "red";
                    } else {
                        winner = "green";
                    }

                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();

                    mediaPlayer.start();
                    Button playAgain = findViewById(R.id.playAgainButton);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        } else if (checkIfallTapped()) {
            Button playAgain = findViewById(R.id.playAgainButton);
            playAgain.setVisibility(View.VISIBLE);
        }
    }

    public void playAgain(View view) {
        mediaPlayer.pause();
        Button playAgain = findViewById(R.id.playAgainButton);
        playAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }


        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        isUserActive = 0;
        someoneHasWon = false;
    }

    private boolean checkIfallTapped() {
        boolean allTapped = false;
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            if (i != 2) {
                allTapped = true;
            }


        }
        return allTapped;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.hyena_laugh);
    }
}
