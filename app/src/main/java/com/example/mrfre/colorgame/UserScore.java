package com.example.mrfre.colorgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UserScore extends AppCompatActivity {


    //used for displaying the user score
    int score = 0;
    String sessionID;
    TextView display;

    //used to check the user score
    int[] highscores = new int[3];
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_score);
        display = (TextView)findViewById(R.id.userScore);
        displayScore();
        //checkScore();
    }

    //uses score from GamePlay activity and displays it on screen
    private void displayScore(){
        sessionID = getIntent().getStringExtra("userScore");
        score = Integer.parseInt(sessionID);
        display.setText("Your Score: " + score);
    }


//    private void checkScore(){
//        try{
//                FileReader myFile = new FileReader("Highscores.txt");
//                Scanner read = new Scanner(myFile);
//                if(read.nextLine().length() == 0){
//                System.out.println("congrats on a new highscore!");
//            }
//            else{
//                while(read.hasNextLine()){
//                    highscores[i] = Integer.parseInt(read.nextLine());
//                    i++;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
