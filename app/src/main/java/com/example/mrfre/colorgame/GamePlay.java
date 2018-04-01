package com.example.mrfre.colorgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GamePlay extends AppCompatActivity {


    int score = 0;

    private ImageButton red;
    private ImageButton green;
    private ImageButton blue;
    private ImageButton magenta;
    private ImageButton yellow;
    private TextView wordDisplay;
    private TextView displayScore;
    private TextView time;

    //used for word selection and color
    Random rng = new Random();
    private String[] wordBank = {"red","purple","blue","green","yellow"};
    String word = "";
    String wordColor = "";
    int num = 0;
    String circleColor;

    //used for button positioning, max x is 950 for vertical screen, max y is 1450 for verical screen
    float [] xAxes = new float[5];
    float [] yAxes = new float[5];
    int xIndex = -1;
    int yIndex = -1;
    float xAxis = 0;
    float yAxis = 0;
    boolean enoughSpace = false;


    int[] dummyScores = {3, 2, 1};
    int ind = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        red = (ImageButton)findViewById(R.id.redCircle);
        green = (ImageButton)findViewById(R.id.greenCircle);
        blue = (ImageButton)findViewById(R.id.blueCircle);
        magenta = (ImageButton)findViewById(R.id.magentaCircle);
        yellow = (ImageButton)findViewById(R.id.yellowCircle);
        wordDisplay = (TextView)findViewById(R.id.gameWord);
        displayScore = (TextView)findViewById(R.id.score);
        time = (TextView)findViewById(R.id.timer);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if(score > dummyScores[2]){
                    findIndex();
                    Intent nhs = new Intent(getApplicationContext() , NewHighScore.class);
                    nhs.putExtra("userScore", "" + score);
                    nhs.putExtra("index", "" + ind);
                    startActivity(nhs);
                }
                else{
                    Intent sm = new Intent(getApplicationContext() , UserScore.class);
                    sm.putExtra("userScore", "" + score);
                    startActivity(sm);
                }
            }
        }.start();
        run();
    }


    private void run(){
        selectWord();
        selectWordColor();
        setWord();
        test();
        //setButtonLocations();
    }


    //finds index where new high score should be inserted
    private void findIndex(){
        for(int i =0; i<dummyScores.length;i++){
            if(score >= dummyScores[i]){
                ind = i;
                shift(i);
                return;
            }
        }
    }

    //inserts new high score, shifts appropriate scores down
    private void shift(int index){
        int nextInsert = score;
        int temp;
        while(index <= 2){
            temp = dummyScores[index];
            dummyScores[index] = nextInsert;
            nextInsert = temp;
            index++;
        }
    }
    //set the word that has to be matched with the circle and sets color of the word for the game
    private void setWord(){
        wordDisplay.setText(word);
        switch(wordColor){
            case "red":
                wordDisplay.setTextColor(Color.RED);
                break;
            case "purple":
                wordDisplay.setTextColor(Color.MAGENTA);
                break;
            case "blue":
                wordDisplay.setTextColor(Color.CYAN);
                break;
            case "green":
                wordDisplay.setTextColor(Color.GREEN);
            case "yellow":
            default:
                break;
        }
    }


    //uses upper and lower bounds for an rng number between 0-999 to randomly assign a word from the word bank to word string
    private void selectWord(){
        num = rng.nextInt(1000);
        if(num >= 0 && num <= 199){
            word = wordBank[0];
        }
        else if(num >= 200 && num <= 399){
            word = wordBank[1];
        }
        else if(num >= 400 && num <= 599){
            word = wordBank[2];
        }
        else if(num >= 600 && num <= 799){
            word = wordBank[3];
        }
        else {
            word = wordBank[4];
        }
    }

    //selects a word from the word bank and assigns it to wordColor string
    private void selectWordColor(){
        num = rng.nextInt(1000);
        if(num >= 0 && num <= 199){
            wordColor = wordBank[0];
        }
        else if(num >= 200 && num <= 399){
            wordColor = wordBank[1];
        }
        else if(num >= 400 && num <= 599){
            wordColor = wordBank[2];
        }
        else if(num >= 600 && num <= 799){
            wordColor = wordBank[3];
        }
        else {
            wordColor = wordBank[4];
        }
    }

    //make sure buttons dont overlap
    private void getXAxes(){
        xAxis = rng.nextInt(950);
        while(xAxis > 250 && xAxis < 700){
            xAxis = rng.nextInt(950);
        }
    }

    private void getYAxes(){
        yAxis = rng.nextInt(1450);
        while(yAxis > 50 && yAxis < 350){
            yAxis = rng.nextInt(1450);
        }
    }


    private void checkBuffer(int key){
        if(key == 0){   //0 is key for xAxis array
            for(int i =0; i <= xIndex; i++){
                if(Math.abs(xAxes[i] - xAxis) < 100){
                    enoughSpace = true;
                }
                else
                    enoughSpace = false;
            }
        }
        else{
            for(int i =0; i <= yIndex; i++){
                if(Math.abs(yAxes[i] - yAxis) < 100){
                    enoughSpace = true;
                }
                else
                    enoughSpace = false;
            }
        }
    }

    private void updateIndices(){
        xIndex++;
        xAxes[xIndex] = xAxis;
        yIndex++;
        yAxes[yIndex] = yAxis;
    }

    //250 > x > 700 and 50 > y > 350
    //sets the x and y axis values for each button's positioning
    private void setButtonLocations(){
        for(int i =0; i < 5; i++){
            switch(i){
                case 0:
                    getXAxes();
                    getYAxes();
                    updateIndices();
                    red.setX(xAxis);
                    red.setY(yAxis);
                    break;
                case 1:
                    while(!enoughSpace){
                        getXAxes();
                        checkBuffer(0);
                    }
                    while(!enoughSpace){
                        getYAxes();
                        checkBuffer(1);
                    }
                    updateIndices();
                    magenta.setX(xAxis);
                    magenta.setY(yAxis);
                    break;
                case 2:
                    while(!enoughSpace){
                        getXAxes();
                        checkBuffer(0);
                    }
                    while(!enoughSpace){
                        getYAxes();
                        checkBuffer(1);
                    }
                    updateIndices();
                    blue.setX(xAxis);
                    blue.setY(yAxis);
                    break;
                case 3:
                    while(!enoughSpace){
                        getXAxes();
                        checkBuffer(0);
                    }
                    while(!enoughSpace){
                        getYAxes();
                        checkBuffer(1);
                    }
                    updateIndices();
                    green.setX(xAxis);
                    green.setY(yAxis);
                    break;
                case 4:
                    while(!enoughSpace){
                        getXAxes();
                        checkBuffer(0);
                    }
                    while(!enoughSpace){
                        getYAxes();
                        checkBuffer(1);
                    }
                    updateIndices();
                    yellow.setX(xAxis);
                    yellow.setY(yAxis);
                    break;
                default:
                    break;
            }
        }
    }


    //resets variables pertaining to the locations of the circles
    private void reset(){
        for(int i = 0; i < xAxes.length; i++){
            xAxes[i] = 0;
        }
        for(int i = 0; i < yAxes.length; i++){
            yAxes[i] = 0;
        }
        yIndex = -1;
        xIndex = -1;
    }

    private void test(){
        for(int i =0; i < 5; i++){
            switch(i){
                case 0:
                    getXAxes();
                    getYAxes();
                    red.setX(xAxis);
                    red.setY(yAxis);
                    break;
                case 1:
                    getXAxes();
                    getYAxes();
                    magenta.setX(xAxis);
                    magenta.setY(yAxis);
                    break;
                case 2:
                    getXAxes();
                    getYAxes();
                    blue.setX(xAxis);
                    blue.setY(yAxis);
                    break;
                case 3:
                    getXAxes();
                    getYAxes();
                    green.setX(xAxis);
                    green.setY(yAxis);
                    break;
                case 4:
                    getXAxes();
                    getYAxes();
                    yellow.setX(xAxis);
                    yellow.setY(yAxis);
                    break;
                default:
                    break;
            }
        }
    }


    //checks to see if word and circle color match; updates score
    private void ifMatch(){
        if(circleColor.equals(word)){
            score++;
            displayScore.setText("# Correct: " + score);
        }
        else if(score == 0){
            return;
        }
        else{
            score--;
            displayScore.setText("# Correct: " + score);
            return;
        }
    }


    //onClickListener for magenta circle
    public void magClick(View view) {
        circleColor = "purple";
        ifMatch();
        reset();
        run();
    }

    //onClickListener for green circle
    public void greenClick(View view) {
        circleColor = "green";
        ifMatch();
        reset();
        run();
    }

    //onClickListener for red circle
    public void redClick(View view) {
        circleColor = "red";
        ifMatch();
        reset();
        run();
    }

    //onClickListener for blue circle
    public void blueClick(View view) {
        circleColor = "blue";
        ifMatch();
        reset();
        run();
    }

    //onClickListener for yellow circle
    public void yellowClick(View view) {
        circleColor = "yellow";
        ifMatch();
        reset();
        run();
    }
}
