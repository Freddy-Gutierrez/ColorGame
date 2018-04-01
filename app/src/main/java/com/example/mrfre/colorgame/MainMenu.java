package com.example.mrfre.colorgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void start(View view) {
        Intent gamePlayActivity = new Intent(this,GamePlay.class);
        startActivity(gamePlayActivity);
    }

    public void highScores(View view) {
        Intent hs = new Intent(MainMenu.this,HighScores.class);
        startActivity(hs);
    }
}
