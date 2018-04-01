package com.example.mrfre.colorgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighScores extends AppCompatActivity {

    TextView usernameOne;
    TextView highscoreOne;
    TextView spacerOne;
    TextView usernameTwo;
    TextView highscoreTwo;
    TextView spacerTwo;
    TextView usernameThree;
    TextView highscoreThree;
    TextView spacerThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        usernameOne = (TextView)findViewById(R.id.nameOne);
        highscoreOne = (TextView)findViewById(R.id.scoreOne);
        spacerOne = (TextView)findViewById(R.id.periods);
        usernameTwo = (TextView)findViewById(R.id.nameTwo);
        highscoreTwo = (TextView)findViewById(R.id.scoreTwo);
        spacerTwo = (TextView)findViewById(R.id.periodsTwo);
        usernameThree = (TextView)findViewById(R.id.nameThree);
        highscoreThree = (TextView)findViewById(R.id.scoreThree);
        spacerThree = (TextView)findViewById(R.id.periodsThree);
        display();
    }

    private void display(){
        usernameOne.setText(getIntent().getStringExtra("username"));
        highscoreOne.setText(getIntent().getStringExtra("highscore"));
    }

    public void mmClick(View view) {
        Intent backToMain = new Intent(this, MainMenu.class);
        startActivity(backToMain);
    }
}
