package com.kmilo.pablodemo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kmilo.pablodemo.R;

public class ConnectGameActivity extends AppCompatActivity {

    ImageView imgCounter;
    int activePlayer;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int tappedCounter;
    boolean gameActive;
    TextView txtWinner;
    Button btnPlayAgain;
    GridLayout gridCounters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_game);

        // 0:Yellow 1:Red 2:Empty
        activePlayer = 0;
        gameActive = true;
        txtWinner = findViewById(R.id.txtWinner);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        gridCounters = findViewById(R.id.gridCounters);
        txtWinner.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);
    }

    public void playAgain(View view) {
        txtWinner.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        gameActive = true;
        activePlayer = 0;

        for (int i=0; i<gridCounters.getChildCount(); i++){
            imgCounter = (ImageView) gridCounters.getChildAt(i);
            imgCounter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
    }

    public void dropIn(View view) {

        imgCounter = (ImageView) view;
        tappedCounter = Integer.parseInt(imgCounter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive){

            imgCounter.setTranslationY(-1500);

            if (activePlayer == 0){
                imgCounter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }else {
                imgCounter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            imgCounter.animate().translationYBy(1500).rotationXBy(3600).setDuration(300);
            gameState[tappedCounter] = activePlayer;
            checkIsSomeoneWon();

        }else {
            Toast.makeText(this,R.string.toastIsFullCounter, Toast.LENGTH_SHORT).show();
            btnPlayAgain.setVisibility(View.VISIBLE);
        }
    }

    private void checkIsSomeoneWon() {

        for(int[]eachChildOfWinningPosition:winningPosition){

            if (gameState[eachChildOfWinningPosition[0]] == gameState[eachChildOfWinningPosition[1]]
                    && gameState[eachChildOfWinningPosition[1]] == gameState[eachChildOfWinningPosition[2]]
                    && gameState[eachChildOfWinningPosition[0]] != 2){
                //Someone has Won!
                if (activePlayer == 1){
                    txtWinner.setText(getResources().getString(R.string.userYellow) + " " + getResources().getString(R.string.txtWinner));
                }else {
                    txtWinner.setText(getResources().getString(R.string.userRed) + " " + getResources().getString(R.string.txtWinner));
                }
                txtWinner.setVisibility(View.VISIBLE);
                btnPlayAgain.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }
    }
}