package com.example.thepiggame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ImageView diceImageView, player1LineImageView, player2LineImageView;
    TextView currentScore1TextView, currentScore2TextView, player1ScoreTextView, player2ScoreTextView,
            player1TextView, player2TextView, player1WinsTextView, player2WinsTextView;
    Button rollDiceButton, newGameButton, holdButton;
    Boolean player1CanRoll = false, player2CanRoll = false, player1CanHold = false, player2CanHold = false;
    int playerTurn;
    int currentScore1 = 0, currentScore2 = 0, player1Score = 0, player2Score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diceImageView = findViewById(R.id.diceImageView);
        rollDiceButton = findViewById(R.id.rollDiceButton);
        newGameButton = findViewById(R.id.newGameButton);
        holdButton = findViewById(R.id.holdButton);
        currentScore1TextView = findViewById(R.id.currentScore1TextView);
        currentScore2TextView = findViewById(R.id.currentScore2TextView);
        player1ScoreTextView = findViewById(R.id.player1ScoreTextView);
        player2ScoreTextView = findViewById(R.id.player2ScoreTextView);
        player1TextView = findViewById(R.id.player1TextView);
        player2TextView = findViewById(R.id.player2TextView);
        player1WinsTextView = findViewById(R.id.player1WinsTextView);
        player2WinsTextView = findViewById(R.id.player2WinsTextView);
        player1LineImageView = findViewById(R.id.player1LineImageView);
        player2LineImageView = findViewById(R.id.player2LineImageView);

        player1WinsTextView.setAlpha(0);
        player2WinsTextView.setAlpha(0);
        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player1CanRoll || player2CanRoll) {
                    int MIN = 1, MAX = 6;
                    int randomInt = (int) (MIN + Math.random() * (MAX - MIN + 1));
                    switch (randomInt) {
                        case 1:
                            diceImageView.setImageResource(R.drawable.dado1);
                            break;
                        case 2:
                            diceImageView.setImageResource(R.drawable.dado2);
                            break;
                        case 3:
                            diceImageView.setImageResource(R.drawable.dado3);
                            break;
                        case 4:
                            diceImageView.setImageResource(R.drawable.dado4);
                            break;
                        case 5:
                            diceImageView.setImageResource(R.drawable.dado5);
                            break;
                        case 6:
                            diceImageView.setImageResource(R.drawable.dado6);
                            break;
                    }
                    if(playerTurn == 1){
                        currentScore1 += randomInt;
                        currentScore1TextView.setText("" + currentScore1);
                        if(randomInt != 1){
                            player1CanHold = true;
                        }else{
                            currentScore1 = 0;
                            currentScore1TextView.setText("" + currentScore1);
                            updatePlayer2Turn();
                        }
                    }
                    else if(playerTurn == 2){
                        currentScore2 += randomInt;
                        currentScore2TextView.setText("" + currentScore2);
                        if(randomInt != 1){
                            player2CanHold = true;
                        }else{
                            currentScore2= 0;
                            currentScore2TextView.setText("" + currentScore2);
                            updatePlayer1Turn();
                        }
                    }
                }

            }
        });

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentScore1TextView.setText("0");
                currentScore2TextView.setText("0");
                player1ScoreTextView.setText("0");
                player2ScoreTextView.setText("0");
                player1WinsTextView.setAlpha(0);
                player2WinsTextView.setAlpha(0);
                player1CanRoll = true;
                updatePlayer1Turn();
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player1CanHold){
                    player1Score += currentScore1;
                    player1ScoreTextView.setText("" + player1Score);
                    currentScore1 = 0;
                    currentScore1TextView.setText("" + currentScore1);
                    player1CanHold = false;
                    if(player1Score >= 100){
                        player1WinsTextView.setAlpha(1);
                        endGame();
                    }else{
                        updatePlayer2Turn();
                    }

                }
                else if(player2CanHold){
                    player2Score += currentScore2;
                    player2ScoreTextView.setText("" + player2Score);
                    currentScore2 = 0;
                    currentScore2TextView.setText("" + currentScore2);
                    player2CanHold = false;
                    if(player2Score >= 100){
                        player2WinsTextView.setAlpha(1);
                        endGame();
                    }else{
                        updatePlayer1Turn();
                    }
                }
            }
        });

    }
    public void updatePlayer1Turn(){
        playerTurn = 1;
        player1TextView.setText("PLAYER 1");
        player1TextView.setTextColor(Color.rgb(103,58,183));
        player1LineImageView.setAlpha(1f);
        player2TextView.setTextColor(Color.BLACK);
        player2LineImageView.setAlpha(0f);

    }
    public void updatePlayer2Turn(){
        playerTurn = 2;
        player2TextView.setText("PLAYER 2");
        player2TextView.setTextColor(Color.rgb(103,58,183));
        player2LineImageView.setAlpha(1f);
        player1TextView.setTextColor(Color.BLACK);
        player1LineImageView.setAlpha(0f);

    }

    public void endGame(){
        playerTurn = 0;
        player1CanRoll = false;
        player2CanRoll = false;
        player1CanHold = false;
        player2CanHold = false;
    }
}