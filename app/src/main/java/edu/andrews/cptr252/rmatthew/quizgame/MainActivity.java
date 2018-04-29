package edu.andrews.cptr252.rmatthew.quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * A simple menu screen activity that leads to two fragments.
 */
public class MainActivity extends AppCompatActivity {

    private Button mListButton;
    private Button mGameButton;

    /**
     * Creates an intent and starts a new activity.
     */
    private void displayListActivity () {
        Intent i = new Intent(MainActivity.this, QuestionListActivity.class);
        startActivity(i);
    }

    /**
     * Creates an intent and starts a new activity.
     */
    private void displayGameActivity () {
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        startActivity(i);
    }

    /**
     * Method to start the activity and create and implement the interface.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListButton = (Button) findViewById(R.id.questionButton);
        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { displayListActivity(); }

        });

        mGameButton = (Button) findViewById(R.id.gameButton);
        mGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { displayGameActivity(); }
        });
    }



}
