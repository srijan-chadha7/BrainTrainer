package com.example.srijan.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfAnswer;
    TextView textView;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int score=0;
    int numberOfQuestions=0;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("5s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        nextQuestion();
        textView.setText("");
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                textView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);


            }
        }.start();

    }

    public void chooseAnswer(View view){

        if(Integer.toString(locationOfAnswer).equals(view.getTag().toString())){
            textView.setText("Correct!");
            score++;
        }else{
            textView.setText("Incorrect!");
        }
        nextQuestion();
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
    }
    public void nextQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        answers.clear();

        locationOfAnswer=rand.nextInt(4);
        int wrongAnswer=rand.nextInt(41);

        for(int i=0;i<4;i++)
        {
            if(locationOfAnswer==i){
                answers.add(a+b);
            }
            else{
                while(wrongAnswer==a+b){
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view){
        gameLayout.setVisibility(View.VISIBLE);

        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView=findViewById(R.id.sumTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        goButton=findViewById(R.id.goButton);
        textView=findViewById(R.id.textView);
        playAgainButton=findViewById(R.id.playAgainButton);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        gameLayout=findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);







    }
}
