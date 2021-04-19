package com.tni.max.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public TextView player1_score,player2_score,winner;
    public int player1_count,player2_count;
    public Button playAgain;
    public Button button1,button2,button3,button4,button5,button6,button7,button8,button0;

    int play_x = 1;
    int play_o = 0;

    int activePlayer = play_o;

    int [] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean isGameActive = true;

    int winningPositions[][] = {
            {0,1,2},{3,4,5},{6,7,8}, //row
            {0,3,6},{1,4,7},{2,5,8}, //column
            {0,4,8},{2,4,6} //cross
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1_score = findViewById(R.id.score1);
        player2_score = findViewById(R.id.score2);
        winner = findViewById(R.id.winnertext);
        playAgain = findViewById(R.id.newgame);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button0 = findViewById(R.id.button0);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button0.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.d("Button","Button is Clicked");

        if (!isGameActive){
            return;
        }

        Button clickBtn = findViewById(v.getId());
        int isClicked = Integer.parseInt(v.getTag().toString());

        if (filledPos[isClicked] != -1){
            return;
        }

        filledPos[isClicked] = activePlayer;

        if (activePlayer == play_o){
            clickBtn.setText("O");
            activePlayer = play_x;
        }else {
            clickBtn.setText("X");
            activePlayer = play_o;
        }
        checkWinner();

    }

    public void checkWinner(){
        for (int i = 0;i<8;i++){
            int win0 = winningPositions[i][0];
            int win1 = winningPositions[i][1];
            int win2 = winningPositions[i][2];

            if (filledPos[win0] == filledPos[win1] && filledPos[win1] == filledPos[win2]){
                if (filledPos[win0] != -1){
                    isGameActive = false;
                    if (filledPos[win0] == play_o){
                        //winner.setText("O is Winner");
                        Toast.makeText(this,"O Win",Toast.LENGTH_SHORT).show();
                        player1_count++;
                    }
                    else{
                        //winner.setText("X is Winner");
                        Toast.makeText(this,"X Win",Toast.LENGTH_SHORT).show();
                        player2_count++;
                    }
                    restartGame();
                }
            }
            int count = 0;
            for(int j=0;j<9;j++) {
                if(filledPos[j] != -1) {
                    count++;
                    if(count == 9) {
                        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
                        restartGame();
                    }
                }
            }
            updateScore();
        }
    }

    public void updateScore(){
        player1_score.setText(Integer.toString(player1_count));
        player2_score.setText(Integer.toString(player2_count));
    }

    public void restartGame() {
        activePlayer = play_o;
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        //winner.setText("");
        isGameActive = true;

    }

    public void playagain(View view) {
        Log.d("test","Play Again");
        restartGame();
        player1_score.setText("0");
        player2_score.setText("0");
        player1_count = 0;
        player2_count = 0;

    }
}
