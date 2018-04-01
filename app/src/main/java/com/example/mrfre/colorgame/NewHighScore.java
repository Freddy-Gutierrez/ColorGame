package com.example.mrfre.colorgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewHighScore extends AppCompatActivity {

    TextView msg;
    TextView enterName;
    TextView userName;
    Button save;

    String[] names = {"joe", "john", "smith"};
    String newName= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_high_score);
        msg = (TextView)findViewById(R.id.highscoreMsg);
        enterName = (TextView)findViewById(R.id.usernameMsg);
        userName = (TextView)findViewById(R.id.username);
        save = (Button)findViewById(R.id.save);
    }

    public void saveClick(View view) {
        newName = userName.getText().toString();
        shiftNames();
        Intent i = new Intent(getApplicationContext() , HighScores.class);
        i.putExtra("username", newName);
        i.putExtra("highscore", getIntent().getStringExtra("userScore"));
        startActivity(i);
    }

    private void shiftNames(){
        int ind = Integer.parseInt(getIntent().getStringExtra("index"));
        String nextInsert = newName;
        String temp;
        while(ind <= 2){
            temp = names[ind];
            names[ind] = nextInsert;
            nextInsert = temp;
            ind++;
        }
    }
}
