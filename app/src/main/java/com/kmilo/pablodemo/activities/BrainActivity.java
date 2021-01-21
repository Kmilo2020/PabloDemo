package com.kmilo.pablodemo.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.kmilo.pablodemo.R;

import java.util.ArrayList;
import java.util.Random;

public class BrainActivity extends AppCompatActivity {

    Button btnStart;
    ConstraintLayout secondConstraintLayout;
    CountDownTimer mCountDownTimer;
    TextView txtTimer;
    TextView txtResult;
    Button btnPlayAgainBrain;
    boolean isPlaying;
    Random mRandom;
    TextView txtSum;
    int score;
    int numbersOfGames;
    int locationOfCorrectAnswer;
    ArrayList<Integer> answersOfOneTime;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    TextView txtScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain);

        btnStart = findViewById(R.id.btnStart);
        secondConstraintLayout = findViewById(R.id.secondConstraintLayout);
        txtTimer = findViewById(R.id.txtTimer);
        txtResult = findViewById(R.id.txtResult);
        btnPlayAgainBrain = findViewById(R.id.btnPlayAgainBrain);
        txtSum = findViewById(R.id.txtSum);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txtScore = findViewById(R.id.txtScore);
        isPlaying = false;
    }

    public void startPlay(View view) {
        btnStart.setVisibility(View.INVISIBLE);
        secondConstraintLayout.setVisibility(View.VISIBLE);
        activeCounter();
        newGame();
    }

    public void playAgain(View view) {
        txtResult.setText("");
        btnPlayAgainBrain.setVisibility(View.INVISIBLE);
        activeCounter();
        newGame();
        score = 0;
        numbersOfGames = 0;
        txtScore.setText(Integer.toString(score) + "/" + Integer.toString(numbersOfGames));
    }

    private void activeCounter() {
        isPlaying = true;
        mCountDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(String.valueOf((int)millisUntilFinished/1000) + "s");
                Log.i("Timer",String.valueOf((int)millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                txtResult.setText(R.string.txtResultDone);
                btnPlayAgainBrain.setVisibility(View.VISIBLE);
                isPlaying = false;
            }
        }.start();
    }

    private void newGame() {
        mRandom = new Random();
        int a = mRandom.nextInt(25);
        int b = mRandom.nextInt(25);
        int correctAnswer = a + b;
        int wrongAnswers;
        locationOfCorrectAnswer = mRandom.nextInt(4);

        txtSum.setText(Integer.toString(a) + "+" + Integer.toString(b));
        answersOfOneTime = new ArrayList<Integer>();

        for (int i =0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answersOfOneTime.add(correctAnswer);
            }else {
                wrongAnswers = mRandom.nextInt(25);
                while (wrongAnswers == correctAnswer){
                    wrongAnswers = mRandom.nextInt(25);
                }
                Log.i("wrongAnswer",Integer.toString(wrongAnswers));
                answersOfOneTime.add(wrongAnswers);
            }
        }
        btn0.setText(answersOfOneTime.get(0).toString());
        btn1.setText(answersOfOneTime.get(1).toString());
        btn2.setText(answersOfOneTime.get(2).toString());
        btn3.setText(answersOfOneTime.get(3).toString());
    }

    public void chooseAnswer(View view) {
        if (isPlaying){
            Log.i("isPlaying","Yes!!");
            numbersOfGames++;
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
                score++;
                txtResult.setText(R.string.txtResultCorrect);
            }else {
                txtResult.setText(R.string.txtResultWrong);
            }
            txtScore.setText(Integer.toString(score) + "/" + Integer.toString(numbersOfGames));
            newGame();
        }
    }
}